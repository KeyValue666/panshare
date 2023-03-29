package com.panshare.client.utils;

/* loaded from: ThreadLocalUtil.class */
public class ThreadLocalUtil {
    private static final ThreadLocal<Integer> local = new ThreadLocal<>();

    private ThreadLocalUtil() {
    }

    public static void set(Integer userId) {
        local.set(userId);
    }

    public static Integer get() {
        return local.get();
    }
}
