package com.zhang.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 存储数据
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储数据并设置过期时间
     * @param key 键
     * @param value 值
     * @param time 过期时间（秒）
     */
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 获取数据
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除数据
     * @param key 键
     * @return 是否成功
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 批量删除数据（支持通配符）
     * @param pattern 键模式
     * @return 删除的键数量
     */
    public long deleteByPattern(String pattern) {
        return redisTemplate.delete(redisTemplate.keys(pattern));
    }

    /**
     * 检查键是否存在
     * @param key 键
     * @return 是否存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     * @param key 键
     * @param time 过期时间（秒）
     * @return 是否成功
     */
    public boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 获取过期时间
     * @param key 键
     * @return 过期时间（秒）
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
