package com.panshare.manager.config;

import com.panshare.manager.common.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        AuthInterceptor interceptor = new AuthInterceptor();
        registry.addInterceptor(interceptor)
                .excludePathPatterns("/**/login")
                .addPathPatterns("/**");
    }
}
