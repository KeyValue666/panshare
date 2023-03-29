package com.panshare.client.common;

import cn.hutool.json.JSONUtil;
import com.panshare.client.service.UserService;
import com.panshare.client.utils.IPUtils;
import com.panshare.client.utils.JWTUtils;
import com.panshare.client.utils.ThreadLocalUtil;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class JWTInterceptors implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        R error = R.error();
        String token = request.getHeader("token");
        try {
            Integer userId = JWTUtils.verify(token).getClaim("userId").asInt();
            ThreadLocalUtil.set(userId);
            return true;
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            error.message("请先登录！");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSONUtil.toJsonStr(error));
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String ip = IPUtils.getIp(request);
        String visitor = IPUtils.visitor(ip);
        log.info("访问者：【{}】:IP地址【{}】",visitor,ip);
    }
}
