package com.panshare.client.config;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {
    @Bean({"default"})
    public RedisTemplate<String, String> redisTemplate4String(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer(Object.class);
        //GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        //redisTemplate.setValueSerializer(jsonRedisSerializer);
        //redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        redisTemplate.setValueSerializer(genericToStringSerializer);
        redisTemplate.setHashValueSerializer(genericToStringSerializer);
        return redisTemplate;
    }
}
