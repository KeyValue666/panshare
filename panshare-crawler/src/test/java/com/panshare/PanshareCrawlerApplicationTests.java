package com.panshare;

import com.panshare.pojo.Post;
import com.panshare.service.PostService;
import com.panshare.util.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PanshareCrawlerApplicationTests {
    @Autowired
    @Qualifier("default")
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private PostService postService;
    @Test
    public void demo(){
        Post post = new Post();
        post.setPostTagId(1);
        post.setPostTitle("测试");
        post.setPostContent("hellloworls");
        post.setPostUserId(2);
        boolean save = postService.save(post);
        System.out.println(save);
    }
    @Test
    public void demo2() throws IOException {
        Post post = HttpUtil.pageDetail("https://wpxz.org/d/65947");
        System.out.println(post);
        /*Post post = HttpUtil.pageDetail("https://wpxz.org/d/77918");
        System.out.println(post);*/
    }
}
