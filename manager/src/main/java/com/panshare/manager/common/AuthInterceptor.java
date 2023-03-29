package com.panshare.manager.common;

import cn.hutool.json.JSONUtil;
import com.panshare.manager.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Key&Value
 * @Date 2023/2/21 16:08
 * @Version 1.0
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        R error = R.error();
        String token = request.getHeader("token");
        try {
            JWTUtils.verify(token);
            return true;
        } catch (Exception e) {
            log.error("解析token出现错误，错误信息：{}",e.getMessage());
            error.message("认证失败！");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSONUtil.toJsonStr(error));
            return false;
        }
    }
}
