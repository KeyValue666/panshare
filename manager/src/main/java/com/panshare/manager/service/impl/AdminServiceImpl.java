package com.panshare.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.panshare.manager.common.R;
import com.panshare.manager.utils.IPUtils;
import com.panshare.manager.utils.JWTUtils;
import com.panshare.manager.utils.Md5Utils;
import com.panshare.manager.pojo.Admin;
import com.panshare.manager.mapper.AdminMapper;
import com.panshare.manager.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private HttpServletRequest request;

    @Override
    public R login(Admin user) {
        String ip = IPUtils.getIp(request);
        String visitor = "";
        try {
            visitor = IPUtils.visitor(ip);
        } catch (IOException e) {
            log.error("请求地IP址解析错误：{}", e.getMessage());
        }
        log.info("来自【{}】的用户，IP地址为【{}】登录了后台", visitor, ip);
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();
        userPassword = Md5Utils.encodeByMd5(userPassword);
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userName).eq("user_password", userPassword);
        Admin admin = baseMapper.selectOne(wrapper);
        if (admin != null) {
            Map<String, String> map = new HashMap<>();
            map.put("userId", admin.getUserId() + "");
            map.put("userName", admin.getUserName());
            String token = JWTUtils.getToken(map);
            return R.ok().data("token", token);
        }
        return R.error().message("账户或密码错误！");
    }
}
