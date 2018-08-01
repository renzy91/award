/*
package com.renzy.award.award.init;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.renzy.award.award.model.database.Award;
import com.renzy.award.award.model.database.Phase;
import com.renzy.award.award.service.AwardService;
import com.renzy.award.award.service.PhaseService;
import com.renzy.award.common.context.Constants;
import com.renzy.award.common.utils.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * @author renzhiyong
 * @description:
 * @date 2018-07-17 18:41
 *//*

@Component
public class InitAwardPool implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(InitAwardPool.class);
    @Autowired
    private RedisCacheManager redisCacheManager;
    @Autowired
    private AwardService awardService;
    @Autowired
    private PhaseService phaseService;

    @Override
    public void run(String... args) throws Exception {
        log.info(" ==> InitAwardPool: start......");
        if (redisCacheManager.containsKey(redisCacheManager.getKey("pool"))) {
            log.info(" ==> InitAwardPool: already has award pool in redis");
            return;
        }

        Wrapper<Phase> wrapper = new EntityWrapper<Phase>()
                .eq("state", Constants.PHASE_PUB)
                .gt("start_time", new Date())
                .orderBy("start_time");
        List<Phase> phases = phaseService.selectList(wrapper);

        if (CollectionUtils.isEmpty(phases)) {
            log.info(" ==> InitAwardPool: no conform phases need to init");
            return;
        }

        Phase phase = phases.get(0);
        Integer phaseId = phase.getId();

        //select award
        EntityWrapper<Award> wrap = new EntityWrapper<>();
        wrap.eq("phase_id", phaseId)
                .eq("state", 0);
        List<Award> awards = awardService.selectList(wrap);

        Date startTime = phase.getStartTime();
        Date endTime = phase.getEndTime();
        redisCacheManager.putValue(redisCacheManager.getKey("pool-startTime"), String.valueOf(startTime.getTime()));
        redisCacheManager.putValue(redisCacheManager.getKey("pool-endTime"), String.valueOf(endTime.getTime()));

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
        log.info(" ==> InitAwardPool: end......");
    }

}
*/
