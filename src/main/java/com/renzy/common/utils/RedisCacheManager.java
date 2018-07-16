/**
 * Project Name: mq_project
 * File Name: RedisCacheManager.java
 * Package Name: com.huifenqi.mq.cache
 * Date: 2015年11月30日下午7:36:38
 * Copyright (c) 2015, www.huizhaofang.com All Rights Reserved.
 */
package com.renzy.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */

@Component
public class RedisCacheManager {

    public static final Logger log = LoggerFactory.getLogger(RedisCacheManager.class);
    public static final String SMART_PREFIX = "smart-";
    public static final String SMART_RELATION_PREFIX = String.format("%s%s", SMART_PREFIX, "relation-"); // 关联前缀
    public static final String SMART_NETWORK_ORDER = String.format("%s%s", SMART_PREFIX, "network-order"); // 组网zset
    public static final String SMART_PUBLIC_WEIGHT_PREFIX = String.format("%s%s", SMART_PREFIX, "public-weight-"); // 设置公摊权重阻塞队列
    public static final String SMART_POWER_ON_OFF = String.format("%s%s", SMART_PREFIX, "power-"); // 通电断电阻塞队列

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 存储key-value
     *
     * @param key
     * @param value
     * @throws Exception
     */
    public void putValue(String key, String value) throws Exception {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.set(key, value);
        log.info("[REDIS] put k-v key={}, value={}", key, value);
    }

    /**
     * 存储key-value
     *
     * @param key
     * @param value
     * @param timeout 单位为毫秒
     * @throws Exception
     */
    public void putValue(String key, String value, long timeout) throws Exception {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.set(key, value, timeout, TimeUnit.MILLISECONDS);
        log.info("[REDIS] put k-v  key={}, value={}, timeout={}", key, value, timeout);
    }

    /**
     * 批量存储key-value
     *
     * @param values
     * @throws Exception
     */
    public void putValue(Map<String, String> values) throws Exception {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.multiSet(values);
        log.info("[REDIS] put map Map={}", values);
    }

    /**
     * 获取value
     *
     * @param key
     * @return 不存在时，返回null
     * @throws Exception
     */
    public String getValue(String key) throws Exception {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String result = valueOps.get(key);
        log.info("[REDIS] Get k-v key={}, value={}", key, result);
        return StringUtil.isBlank(result) ? null : result;
    }

    /**
     * 批量获取值
     *
     * @param keys
     * @return
     * @throws Exception
     */
    public Map<String, String> multiGetValue(List<String> keys) throws Exception {

        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        List<String> values = valueOps.multiGet(keys);

        HashMap<String, String> results = new HashMap<>();
        if (values != null && !keys.isEmpty()) {
            for (int i = 0; i < keys.size(); i++) {
                String fieldName = keys.get(i);
                String fieldValue = values.get(i);
                // 如果对应的字段为null，则不需要放入
                if (!StringUtil.isBlank(fieldValue)) {
                    results.put(fieldName, fieldValue);
                }
            }
        }
        log.info("[REDIS] Get Map map={}", results);
        return results;
    }

    /**
     * 批量删除key
     *
     * @param keys
     * @throws Exception
     */
    public void delete(Collection<String> keys) throws Exception {

        redisTemplate.delete(keys);

        log.info("[REDIS] Delete keys={}", keys);
    }

    /**
     * 删除key
     *
     * @param key
     * @throws Exception
     */
    public void delete(String key) throws Exception {
        redisTemplate.delete(key);
        log.info("[REDIS] Delete key={}", key);
    }

    /**
     * 更新hash里面的属性字段
     *
     * @param key     键
     * @param hashKey 属性名称
     * @param value   属性值
     * @throws Exception
     */
    public void putHash(String key, String hashKey, String value) throws Exception {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        hashOps.put(key, hashKey, value);
        log.info("[REDIS] Put hash key={},hashKey={},value={}", key, hashKey, value);
    }

    /**
     * 批量更新hash里面的属性字段
     *
     * @param key
     * @param hashProperties 属性集合
     * @throws Exception
     */
    public void putHash(String key, Map<String, String> hashProperties) throws Exception {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        hashOps.putAll(key, hashProperties);
        log.info("[REDIS] Put hash key={},hashKey-value={}", key, hashProperties);
    }

    /**
     * 获取hash的字段值
     *
     * @param key
     * @param hashKey
     * @throws Exception
     */
    public String getHash(String key, String hashKey) throws Exception {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        String result = hashOps.get(key, hashKey);
        log.info("[REDIS] Get hash key={},hashKey={},value={}", key, hashKey, result);
        return StringUtil.isBlank(result) ? null : result;
    }

    /**
     * 批量获取hash的字段值
     *
     * @param key
     * @param hashKeys
     * @return filed的Map集合, filedName-filedValue
     * @throws Exception
     */
    public Map<String, String> multiGetHash(String key, List<String> hashKeys) throws Exception {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        List<String> values = hashOps.multiGet(key, hashKeys);
        HashMap<String, String> results = new HashMap<String, String>();
        if (values != null && !values.isEmpty()) {
            for (int i = 0; i < hashKeys.size(); i++) {
                String fieldName = hashKeys.get(i);
                String fieldValue = values.get(i);
                // 如果对应的字段为null，则不需要放入
                if (!StringUtil.isBlank(fieldValue)) {
                    results.put(fieldName, fieldValue);
                }
            }
        }
        log.info("[REDIS] Get hash key={},hashKey-value={}", key, results);
        return results;
    }

    /**
     * 获取结果
     *
     * @param key
     * @return
     * @throws Exception
     */
    public Map<String, String> getHashValues(String key) throws Exception {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        Map<String, String> results = hashOps.entries(key);
        log.info("[REDIS] Get hash key={},hashKey-value={}", key, results);
        return results;
    }

    /**
     * 从hash表里面删除对应的值
     *
     * @param key
     * @param hashKey
     * @throws Exception
     */
    public void deleteHash(String key, String hashKey) throws Exception {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        hashOps.delete(key, hashKey);
        log.info("[REDIS] Delete hash key={}, hashKey={}", key, hashKey);
    }

    /**
     * 进行递增
     *
     * @param key
     * @param delta
     * @param timeout
     * @return
     * @throws Exception
     */
    public long incrementDelta(String key, long delta, long timeout) throws Exception {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        long result = valueOps.increment(key, delta);
        redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
        log.info("[REDIS] increment key={}, result={}, timeout={}", key, result, timeout);
        return result;
    }

    /**
     * 获取当前系统的key
     *
     * @param key
     * @return
     */
    public String getKey(String key) {
        return SMART_PREFIX + key;
    }

    /**
     * 判断是否
     *
     * @param ammeterSn
     * @return
     */
//    public int isOnline(String ammeterSn) {
//        int online = 0;
//        try {
//            String key = getKey(ammeterSn);
//            String value = getValue(key);
//            if (!StringUtils.isEmpty(value)) {
//                online = 1;
//            }
//        } catch (Exception e) {
//            log.error(" get value error,cause by = {}", e);
//        }
//        return online;
//    }

    // ########################################  redis zset ###########################

    /**
     * zset add
     *
     * @param key
     * @param value
     * @param score
     */
    public void zsetAdd(String key, String value, double score) {
        ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
        opsForZSet.add(key, value, score);
        log.info("[REDIS] zsetAdd key={}, value={}, score={}", key, value, score);
    }

    /**
     * zset add
     *
     * @param key
     * @param value
     */
    public void zsetAdd(String key, String value) {
        zsetAdd(key, value, 1.0);
    }

    /**
     * zset delete
     *
     * @param key
     * @param value
     */
    public void zsetDelete(String key, String value) {
        ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
        opsForZSet.remove(key, value);
        log.info("[REDIS] zsetDelete key={}, value={}", key, value);
    }

    /**
     * zset 增加score值
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Double zsetIncrementScore(String key, String value, double score) {
        ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
        Double result = opsForZSet.incrementScore(key, value, score);
        log.info("[REDIS] zsetIncrementScore key={}, value={}, score={}", key, value, score);
        return result;
    }

    /**
     * zset 获取score值
     *
     * @param key
     * @param value
     * @return
     */
    public Double zsetScore(String key, String value) {
        ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
        Double score = opsForZSet.score(key, value);
        log.info("[REDIS] zsetScore key={}, value={}", key, value);
        return score;
    }

    /**
     * zset 根据score范围删除元素
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zsetDeleteRangeByScore(String key, double min, double max) {
        ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
        Long result = opsForZSet.removeRangeByScore(key, min, max);
        log.info("[REDIS] zsetDeleteRangeByScore key={}, min={}, max={}", key, min, max);
        return result;
    }

    /**
     * zset 获取所有元素
     *
     * @param key
     * @return
     */
    public Map<String, Double> zsetScan(String key) {
        ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
        Cursor<ZSetOperations.TypedTuple<String>> cursor = opsForZSet.scan(key, ScanOptions.NONE);
        Map<String, Double> map = new HashMap<>();
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple<String> item = cursor.next();
            map.put(item.getValue(), item.getScore());
        }
        log.info("[REDIS] zsetScan key={}, map={}", key, map);
        return map;
    }

    // ########################################  redis list ###########################

    /**
     * 左侧插入
     *
     * @param key
     * @param value
     */
    public void leftPush(String key, String value) {
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        opsForList.leftPush(key, value);
        log.info("[REDIS] leftpush k-v key={}, value={}", key, value);
    }

    /**
     * 左侧阻塞获取
     *
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    public String bleftPop(String key, long timeout, TimeUnit timeUnit) {
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        String value = opsForList.leftPop(key, timeout, timeUnit);
        log.info("[REDIS] leftpop k-v key={}, value={}", key, value);
        return value;
    }

    /**
     * 左侧阻塞获取，超时时间单位为秒
     *
     * @param key
     * @param timeout
     * @return
     */
    public String bleftPop(String key, long timeout) {
        return bleftPop(key, timeout, TimeUnit.SECONDS);
    }


}
