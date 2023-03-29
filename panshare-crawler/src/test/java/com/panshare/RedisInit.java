package com.panshare;

import com.panshare.common.URLProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

/**
 * @Author Key&Value
 * @Date 2023/3/10 18:01
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisInit {
    @Autowired
    @Qualifier("default")
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void initRedis() {
        //对网站一进行初始化
        /*URLProperties.COMPLEX_TYPE.forEach((k, v) -> {
            String key = URLProperties.SITE_COMPLEX + ":" + v;
            for (int i = 0; i < 500; i++) {
                redisTemplate.opsForSet().add(key, i + "");
            }
        });*/
        //对网站二进行初始化
        URLProperties.COMMON_TYPE.forEach((k, v) -> {
            String key = URLProperties.SITE_COMMON + ":" + v;
            for (int i = 0; i < 500; i++) {
                redisTemplate.opsForSet().add(key, i + "");
            }
        });
    }
}
