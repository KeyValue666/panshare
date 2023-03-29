package com.panshare.handler;

import com.panshare.mapper.PostMapper;
import com.panshare.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author Key&Value
 * @Date 2023/3/10 18:16
 * @Version 1.0
 */
@Component
@Slf4j
public class PageHandler {
    @Autowired
    @Qualifier("default")
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private PostService postService;
    //线程池
    private static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    /**
     * @param seedList 种子集合
     * @param type     种子分类
     * @param typeId   分类id
     */
    public void handler(List<String> seedList, String type, Integer typeId) {
        //1.redis去重(避免请求重复的页面)
        List<String> list = seedList.stream().filter((val) -> isExist(type, val)).collect(Collectors.toList());
        log.info("去重后的种子链接：{}", list);
        //2.交给Task解析详情页面
        THREAD_POOL.execute(new PageTask(postService, redisTemplate, type, typeId, list));
    }

    private boolean isExist(String key, String value) {
        //1.如果不存在这个key，则直接加入队伍中
        if (!Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            return true;
        }
        //2.判断当前链接是否在这个网站分类的集合中
        return !Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }
}
