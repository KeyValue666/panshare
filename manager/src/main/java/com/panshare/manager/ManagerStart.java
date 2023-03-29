package com.panshare.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Key&Value
 * @Date 2023/2/21 8:31
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.panshare.manager.mapper")
public class ManagerStart {
    public static void main(String[] args) {
        SpringApplication.run(ManagerStart.class, args);
    }
}
