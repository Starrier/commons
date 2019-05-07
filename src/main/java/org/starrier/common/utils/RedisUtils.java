package org.starrier.common.utils;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * Redis Utils
 *
 * @author imperator
 * @date 2019-10-15
 */
public class RedisUtils {

    @Resource
    private StringRedisTemplate redisTemplate;

    public Boolean existKeyInHash(String hashName, String key) {
        return redisTemplate.opsForHash().hasKey(hashName, key);
    }

    public void putKeyValueInHash(String hashName, String key, String value) {
        redisTemplate.opsForHash().put(hashName, key, value);
    }

}
