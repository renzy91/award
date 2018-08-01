package com.renzy.award.award.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-07-20 11:21
 */
@Component
public class AwardPoolInitListenerSubscriber {
    private final Logger log = LoggerFactory.getLogger(AwardPoolInitListenerSubscriber.class);
    @Value("${redis.database}")
    private String database;
    @Autowired
    private AwardPoolInitListener awardPoolInitListener;
    @Autowired
    private JedisPool jedisPool;

    @PostConstruct
    public void process() {
        new Thread(() -> {
            Jedis jedis = jedisPool.getResource();
            jedis.psubscribe(awardPoolInitListener,  String.format("%s%s%s", "__key*@", database, "__:*"));
        }).start();
    }

}
