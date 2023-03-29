package com.panshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PanshareCrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanshareCrawlerApplication.class, args);
    }

}
