package com.panshare.handler;

import com.panshare.mapper.PostMapper;
import com.panshare.pojo.Post;
import com.panshare.service.PostService;
import com.panshare.util.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 请求解析页面
 *
 * @Author Key&Value
 * @Date 2023/3/10 20:43
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@Slf4j
public class PageTask implements Runnable {
    private PostService postService;
    private RedisTemplate<String, String> redisTemplate;
    private String type;
    private String key;
    private Integer typeId;
    private List<String> seedList;

    @Override
    public void run() {
        List<Post> res = new ArrayList<>();
        log.info("详情页面请求列表：{}", seedList);
        for (String seed : seedList) {
            try {
                Post post = HttpUtil.pageDetail(seed);
                post.setPostTagId(typeId);
                res.add(post);
                //放入redis中
                redisTemplate.opsForSet().add(type, seed);
            } catch (IOException e) {
                log.error("页面{}请求出错，错误信息：{}", seed, e.getMessage());
            }
        }
        //数据保存入库
        postService.saveBatch(res);
        log.info("线程{}入库成功！共{}条", Thread.currentThread().getName(), res.size());
    }

    public PageTask(PostService postService, RedisTemplate<String, String> redisTemplate, String type, Integer typeId, List<String> seedList) {
        this.postService = postService;
        this.redisTemplate = redisTemplate;
        this.type = type;
        this.typeId = typeId;
        this.seedList = seedList;
    }
}
