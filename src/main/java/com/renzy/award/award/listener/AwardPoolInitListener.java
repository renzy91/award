package com.renzy.award.award.listener;

import com.renzy.award.award.service.AwardService;
import com.renzy.award.common.utils.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-07-19 19:25
 */
@Component
public class AwardPoolInitListener extends JedisPubSub{
    private final Logger log = LoggerFactory.getLogger(AwardPoolInitListener.class);
    @Autowired
    private AwardService awardService;

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        log.info("onPSubscribe "
                + pattern + "  " + subscribedChannels + "  cscs");
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        log.info("onPMessage pattern = {}, channel = {},  message = {} osos", pattern, channel, message);
        if (message.startsWith(RedisCacheManager.AWARD_PUBLISH)) {
            awardService.initAwardPool(Integer.valueOf(message.replace(RedisCacheManager.AWARD_PUBLISH, "")));
        }
        log.info(" ==> onMessage: end");
    }

}
