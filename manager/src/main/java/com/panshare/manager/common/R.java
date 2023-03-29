package com.panshare.manager.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private R() {
    }

    public static R ok() {
        R r = new R();
        r.setCode(Code.SUCCESS);
        r.setMessage(Code.SUCCESS_MESSAGE);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(Code.ERROR);
        r.setMessage(Code.ERROR_MESSAGE);
        return r;
    }

    public static R fatal() {
        R r = new R();
        r.setCode(Code.FATAL);
        r.setMessage(Code.FATAL_MESSAGE);
        return r;
    }

    public R message(String message) {
        setMessage(message);
        return this;
    }

    public R code(Integer code) {
        setCode(code);
        return this;
    }

    public R data(Map<String, Object> data) {
        setData(data);
        return this;
    }

    public R data(String name, Object data) {
        this.data.put(name, data);
        return this;
    }
}
