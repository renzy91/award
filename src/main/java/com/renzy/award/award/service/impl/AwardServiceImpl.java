package com.renzy.award.award.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.renzy.award.award.dao.AwardDao;
import com.renzy.award.award.model.database.Award;
import com.renzy.award.award.model.database.Phase;
import com.renzy.award.award.model.database.Record;
import com.renzy.award.award.service.AwardService;
import com.renzy.award.award.service.PhaseService;
import com.renzy.award.award.service.RecordService;
import com.renzy.award.common.exception.BaseException;
import com.renzy.award.common.exception.NormalException;
import com.renzy.award.common.model.BaseResponse;
import com.renzy.award.common.utils.RedisCacheManager;
import com.renzy.award.common.utils.StringUtil;
import com.renzy.award.common.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 奖品表 服务实现类
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-17
 */
@Service
public class AwardServiceImpl extends ServiceImpl<AwardDao, Award> implements AwardService {
    private final Logger log = LoggerFactory.getLogger(AwardServiceImpl.class);
    @Autowired
    private RedisCacheManager redisCacheManager;
    @Autowired
    private RecordService recordService;
    @Autowired
    private AwardService awardService;
    @Autowired
    private PhaseService phaseService;

    @Override
    public BaseResponse<Boolean> draw(Integer userId) {
        try {
            String startTime = redisCacheManager.getValue(redisCacheManager.getKey("pool-startTime"));
            String endTime = redisCacheManager.getValue(redisCacheManager.getKey("pool-endTime"));
            if (StringUtil.isBlank(startTime) || Long.valueOf(startTime).compareTo(System.currentTimeMillis()) > 0) {
                log.error(" ==> draw: not start");
                throw new NormalException("未开始");
            }
            if (StringUtil.isBlank(endTime) || Long.valueOf(endTime).compareTo(System.currentTimeMillis()) < 0) {
                log.error(" ==> draw: end");
                throw new NormalException("已结束");
            }

            String result = doDraw(userId, endTime);
            if (StringUtil.isBlank(result)) {
                log.info(" ==> draw: fail step 2");
                return Utility.getRightResponse(false);
            }
            String[] str = result.split(":");

            saveRecord(userId, str);
            return Utility.getRightResponse(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException(e.getMessage());
        }
    }

    private void saveRecord(Integer userId, String[] str) {
        Record record = new Record();
        record.setPhaseId(Integer.valueOf(str[1]));
        record.setAwardId(Integer.valueOf(str[2]));
        record.setUserId(userId);
        record.setState(0);
        record.setCreateTime(new Date(Long.valueOf(str[0])));
        recordService.insert(record);
    }

    private String doDraw(Integer userId, String endTime) {
        String key = redisCacheManager.getKey("pool-release");
        String value = redisCacheManager.indexOf(key, 0);
        if (Long.valueOf(value).compareTo(System.currentTimeMillis()) > 0) {
            return null;
        }

StringRedisTemplate redisTemplate = redisCacheManager.getRedisTemplate();
List<String> result = redisTemplate.execute(new SessionCallback<List<String>>() {
    @Nullable
    @Override
    public List<String> execute(RedisOperations redisOperations) throws DataAccessException {
        redisOperations.watch(key);
        redisOperations.watch(redisCacheManager.getKey("user-"+userId));

        Object v = redisOperations.opsForList().index(key, 0);
        Object uid = redisOperations.opsForValue().get(redisCacheManager.getKey("user-" + userId));
        if (!value.equals(v) || uid != null) {
            log.info(" ==> draw: fail step 1");
            return null;
        }

        redisOperations.multi();
        redisOperations.opsForList().leftPop(key);
        redisOperations.opsForSet().pop(redisCacheManager.getKey("pool"));
        redisOperations.opsForValue().set(String.format("%s%s", RedisCacheManager.AWARD_USER_PREFIX, userId), "1", Long.valueOf(endTime) - System.currentTimeMillis());
        return redisOperations.exec();
    }
});

        if (result == null) {
            return null;
        }
        return result.get(1);
    }

    @Override
    public void initAwardPool(Integer phaseId) {
        log.info(" ==> initAwardPool: start......");

        Phase phase = phaseService.selectById(phaseId);

        //select award
        EntityWrapper<Award> wrap = new EntityWrapper<>();
        wrap.eq("phase_id", phaseId)
                .eq("state", 0);
        List<Award> awards = awardService.selectList(wrap);

        Date startTime = phase.getStartTime();
        Date endTime = phase.getEndTime();
        try {
            redisCacheManager.putValue(redisCacheManager.getKey("pool-startTime"), String.valueOf(startTime.getTime()));
            redisCacheManager.putValue(redisCacheManager.getKey("pool-endTime"), String.valueOf(endTime.getTime()));
            redisCacheManager.delete(redisCacheManager.getKey("pool-release"));
            redisCacheManager.delete(redisCacheManager.getKey("pool"));

            long t = (endTime.getTime() - startTime.getTime()) / awards.stream().collect(Collectors.reducing(0, Award::getCount, Integer::sum));
            long releaseTime;
            int n = 0;
            for (Award award : awards) {
                Integer count = award.getCount();
                while (count > 0) {
                    releaseTime = startTime.getTime() + t * n++ + (long) (Math.random() * t);
                    redisCacheManager.rightPush(redisCacheManager.getKey("pool-release"), String.valueOf(releaseTime));
                    redisCacheManager.setAdd(redisCacheManager.getKey("pool"), String.format("%s:%s:%s", releaseTime, phaseId, award.getId()));
                    count--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException();
        }
        log.info(" ==> initAwardPool: end......");
    }
}
