package com.panshare.manager.common;

public interface Cache {
    String TAG_CACHE = "tags";
    String TAG_CACHE_NOT_PUBLIC = "tags:public";
    String EMAIL_SEND = "email:";
    String LIKE_POST = "like:0:";
    String LIKE_COMMENT = "like:1:";
    String REPORT = "report:";
    Long TAG_CACHE_TIME = 6L;
    Long EMAIL_SEED_TIME = 5L;
    long IP_MAIL = 1L;//限制同一ip刷短信接口
}
