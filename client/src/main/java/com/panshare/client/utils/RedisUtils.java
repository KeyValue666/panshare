package com.panshare.client.utils;

import cn.hutool.json.JSONUtil;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {
    @Autowired
    @Qualifier("default")
    private RedisTemplate<String, String> redisTemplate;

    public void set(String key, Object value) {
        String s = JSONUtil.toJsonStr(value);
        this.redisTemplate.opsForValue().set(key, s);
    }

    public void set(String key, Object value, long expire, TimeUnit timeUnit) {
        String s = JSONUtil.toJsonStr(value);
        this.redisTemplate.opsForValue().set(key, s, expire, timeUnit);
    }

    public void del(String key) {
        this.redisTemplate.delete(key);
    }

    public <T> T get(String key, Class<T> cls) {
        if (!hasKey(key)) {
            return null;
        }
        String json = this.redisTemplate.opsForValue().get(key);
        return JSONUtil.toBean(json, cls);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean hasKey(String key) {
        if (key == null || key.equals("")) {
            return false;
        }
        return Boolean.TRUE.equals(this.redisTemplate.hasKey(key));
    }

    public void expire(String key, Long time, TimeUnit timeUnit) {
        this.redisTemplate.expire(key, time, timeUnit);
    }

    public void sAdd(String key, String value) {
        this.redisTemplate.opsForSet().add(key, new String[]{value});
    }

    public Long sLen(String key) {
        return this.redisTemplate.opsForSet().size(key);
    }

    public boolean isMember(String key, String value) {
        return Boolean.TRUE.equals(this.redisTemplate.opsForSet().isMember(key, value));
    }

    public void sDel(String key, Object value) {
        this.redisTemplate.opsForSet().remove(key, new Object[]{value});
    }
}
