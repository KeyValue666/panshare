package com.panshare.task;

import cn.hutool.core.util.RandomUtil;
import com.panshare.common.URLProperties;
import com.panshare.handler.PageHandler;
import com.panshare.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


/**
 * @Author Key&Value
 * @Date 2023/3/10 10:13
 * @Version 1.0
 */
@Component
@Slf4j
public class SeedTask implements URLProperties {
    //redis
    @Autowired
    @Qualifier("default")
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private PageHandler pageHandler;

    //网站一种子提取
    @Scheduled(cron = "30 * * * * ?")
    public void seedTaskForComplex() {
        log.warn("对网站【{}】进行爬取....", SITE_COMPLEX);
        int randomType = RandomUtil.randomInt(1, COMPLEX_TYPE.size() + 1);
        String key = SITE_COMPLEX + ":" + COMPLEX_TYPE.get(randomType);
        log.info("key=【{}】",key);
        List<String> pop = redisTemplate.opsForSet().pop(key, SEED_PAGE);
        //当任务集合中为空时，说明任务采集完毕
        if (pop == null || pop.size() == 0) {
            log.error("任务已完成");
            return;
        }
        for (String s : pop) {
            String url = SITE_COMPLEX + COMPLEX_TYPE.get(randomType) + "?page=" + s;
            //发起网络请求，当请求页面出现异常时，将当前页面存入继续存入redis中
            try {
                //1.请求种子页面
                String body = HttpUtil.get(url);
                //2.解析种子页面获取种子
                List<String> seedList = HttpUtil.parseSeed(body);
                //当页面解析不出链接时,说明超出了页面最大页数
                if (seedList.size() <= 0) {
                    log.warn("链接{}超出最大页码", url);
                    continue;
                }
                log.info("种子采集器：{}", seedList);
                //3.交给页面处理器处理详细页面
                pageHandler.handler(seedList, COMPLEX_TYPE.get(randomType), randomType);
            } catch (IOException e) {
                log.error("页面{}请求出错：{}", url, e.getMessage());
                //把请求出错的页面重放redis中
                redisTemplate.opsForSet().add(key, s);
            }
        }
    }

    //网站二种子提取
    @Scheduled(cron = "0/50 * * * * ?")
    public void seedTaskForCommon() {
        log.warn("对网站【{}】进行爬取.....", SITE_COMMON);
        int randomType = RandomUtil.randomInt(1, COMMON_TYPE.size()+1);
        String key = SITE_COMMON + ":" + COMMON_TYPE.get(randomType);
        log.info("key=【{}】",key);
        List<String> pop = redisTemplate.opsForSet().pop(key, SEED_PAGE);
        log.info("pop=【{}】",pop);
        //当任务集合中为空时，说明任务采集完毕
        if (pop == null || pop.size() == 0) {
            log.error("任务已完成");
            return;
        }
        for (String s : pop) {
            String url = SITE_COMMON + COMMON_TYPE.get(randomType) + "?page=" + s;
            //发起网络请求，当请求页面出现异常时，将当前页面存入继续存入redis中
            try {
                //1.请求种子页面
                String body = HttpUtil.get(url);
                //2.解析种子页面获取种子
                List<String> seedList = HttpUtil.parseSeedSiteCommon(body);
                //当页面解析不出链接时,说明超出了页面最大页数
                if (seedList.size() <= 0) {
                    log.warn("链接【{}】超出最大页码", url);
                    continue;
                }
                log.info("种子采集器：【{}】", seedList);
                //3.交给页面处理器处理详细页面
                pageHandler.handler(seedList, COMMON_TYPE.get(randomType), randomType);
            } catch (IOException e) {
                log.error("页面{}请求出错：{}", url, e.getMessage());
                //把请求出错的页面重放redis中
                redisTemplate.opsForSet().add(key, s);
            }
        }
    }
}
