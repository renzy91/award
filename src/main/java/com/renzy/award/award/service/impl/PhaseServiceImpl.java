package com.renzy.award.award.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.renzy.award.award.dao.PhaseDao;
import com.renzy.award.award.model.database.Phase;
import com.renzy.award.award.service.PhaseService;
import com.renzy.award.common.context.Constants;
import com.renzy.award.common.exception.BaseException;
import com.renzy.award.common.exception.NormalException;
import com.renzy.award.common.model.BaseResponse;
import com.renzy.award.common.utils.DateUtil;
import com.renzy.award.common.utils.RedisCacheManager;
import org.apache.ibatis.annotations.One;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 抽奖期数表 服务实现类
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-17
 */
@Service
public class PhaseServiceImpl extends ServiceImpl<PhaseDao, Phase> implements PhaseService {
    private final Logger log = LoggerFactory.getLogger(PhaseServiceImpl.class);
    @Autowired
    private PhaseService phaseService;
    @Autowired
    private RedisCacheManager redisCacheManager;
    private static final long ONE_HOUR = 60*60*1000;
    private static final long HALF_HOUR = 30*60*1000;
    private static final long ONE_DAY = 24*60*60*1000;

    @Override
    public BaseResponse<Boolean> add(Phase phase) {

        if (phase.getStartTime().getTime()%ONE_HOUR != 0 && phase.getStartTime().getTime()%ONE_HOUR != HALF_HOUR ) {
            throw new NormalException("开始时间错误");
        }
        if (phase.getEndTime().getTime() - phase.getEndTime().getTime() < 5*60*1000) {
            throw new NormalException("结束时间错误");
        }

        StringRedisTemplate redisTemplate = redisCacheManager.getRedisTemplate();
        redisTemplate.execute(new SessionCallback<List<String>>() {
            @Nullable
            @Override
            public List<String> execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.watch(RedisCacheManager.AWARD_PHASE_ARRANGE);
                Object o = redisOperations.opsForValue().get(RedisCacheManager.AWARD_PHASE_ARRANGE);

                List<Integer> list = null;
                if(o == null) {
                    list = new ArrayList<>(48);
                }
//                list.add(phase.getStartTime().getTime()%ONE_HOUR%24, 1);



                return null;
            }
        });

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publish(Integer phaseId) {
        Phase phase = phaseService.selectById(phaseId);

        if (phase == null || phase.getState() == Constants.PHASE_DEL)
            throw new NormalException("不存在");
        if (phase.getState() != Constants.PHASE_NEW)
            throw new NormalException("状态不正确");
        if (phase.getStartTime().getTime() <= DateUtil.addMinutes(new Date(), 10).getTime())
            throw new NormalException("开始时间不能小于当前时间10分钟");


        phase.setState(Constants.PHASE_PUB);
        phase.setUpdateTime(new Date());
        phaseService.updateById(phase);

        long timeOut = phase.getStartTime().getTime() - System.currentTimeMillis() - Constants.PUBLISH_TIME_ADVANCE;
        try {
            redisCacheManager.putValue(String.format("%s%s", RedisCacheManager.AWARD_PUBLISH, phaseId), String.valueOf(phaseId), timeOut);
        } catch (Exception e) {
            throw new NormalException("失败");
        }
    }

    public static void main(String[] args) {
        Date phase = DateUtil.parseDateTime("2017-08-09 01:30:00");
        System.out.println(phase.getTime()%ONE_DAY);
    }
}
