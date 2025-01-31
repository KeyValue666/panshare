package com.panshare.client.controller;

import com.panshare.client.common.Limit;
import com.panshare.client.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author Key&Value
 * @Date 2024/5/6 18:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/limit")
public class TestLimit {
    @GetMapping("/test")
    @Limit(limit = 5, interval = 10, time = TimeUnit.SECONDS)
    public R test() {
        return R.ok();
    }
}
