package com.panshare.client.config;

import com.panshare.client.common.JWTInterceptors;

import java.util.ArrayList;
import java.util.List;

import com.panshare.client.common.LimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LimitInterceptor limitInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        List<String> black = new ArrayList<>();//黑名单
        black.add("/user/**");
        black.add("/post/upload");
        black.add("/post/delete");
        black.add("/like/**");
        black.add("/comment/publish");
        black.add("/comment/delete");
        black.add("/upload/**");
        black.add("/report/**");
        List<String> white = new ArrayList<>();//白名单
        white.add("/user/login");
        white.add("/user/registry");
        white.add("/user/userDetail/**");
        white.add("/user/code");
        registry.addInterceptor(new JWTInterceptors()).addPathPatterns(black).excludePathPatterns(white);
        registry.addInterceptor(limitInterceptor).addPathPatterns("/**");
    }
}
