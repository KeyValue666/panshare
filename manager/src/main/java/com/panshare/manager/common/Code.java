package com.panshare.manager.common;

public interface Code {
    public static final String SUCCESS_MESSAGE = "请求成功";
    public static final String AUTH_MESSAGE = "鉴权失败";
    public static final String ERROR_MESSAGE = "请求错误";
    public static final String ERROR_Valid_MESSAGE = "请求参数不合法";
    public static final String FATAL_MESSAGE = "系统异常，请稍后重试";
    public static final Integer SUCCESS = 200;
    public static final Integer AUTH_ERROR = 300;
    public static final Integer ERROR = 400;
    public static final Integer ERROR_Valid = 401;
    public static final Integer FATAL = 500;
}
