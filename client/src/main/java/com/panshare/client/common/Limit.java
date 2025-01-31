package com.panshare.client.common;

import lombok.Data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @Author Key&Value
 * @Date 2024/5/6 17:55
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {
    int limit();//限制次数

    int interval();//时间间隔

    TimeUnit time() default TimeUnit.MINUTES;//时间单位
}
