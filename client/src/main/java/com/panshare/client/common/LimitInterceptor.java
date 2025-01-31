package com.panshare.client.common;

import com.panshare.client.utils.RedisUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Author Key&Value
 * @Date 2024/5/6 17:59
 * @Version 1.0
 */
@Configuration
@Slf4j
public class LimitInterceptor implements HandlerInterceptor {
    private static final String LOCK = "lock:";
    private static final int BAN_TIME = 1;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String host = request.getRemoteHost();
        String pre = uri + ":" + host;//唯一值
        if (redisUtils.hasKey(LOCK + pre)) {
            throw new PanShareException("请求太过频繁，请稍后重试！");//已经被封锁
        }
        if (handler instanceof HandlerMethod) {
            // 访问的是接口方法，转化为待访问的目标方法对象
            HandlerMethod targetMethod = (HandlerMethod) handler;
            Limit limit = targetMethod.getMethodAnnotation(Limit.class);
            //如果不存在Limit注解，则接口选择直接放行
            if (limit != null) {
                int count = limit.limit();
                int interval = limit.interval();
                TimeUnit time = limit.time();
                if (redisUtils.hasKey(pre)) {
                    log.info("请求次数：${}",redisUtils.get(pre));
                    int curCount = Integer.parseInt(redisUtils.get(pre));
                    if (curCount >= count) {
                        //封锁
                        log.error("【IP为:${}】因请求次数过多已被封锁！", host);
                        redisUtils.set(LOCK + pre, true, BAN_TIME, TimeUnit.MINUTES);
                        throw new PanShareException("请求太过频繁，请稍后重试！");
                    }
                    redisUtils.incr(pre);//增加一
                } else {
                    log.info("【第一次访问】");
                    redisUtils.setex(pre, 1, interval, time);
                }
            }
        }
        return true;
    }
}
