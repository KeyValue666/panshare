package com.panshare.client.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panshare.client.common.Cache;
import com.panshare.client.common.PanShareException;
import com.panshare.client.common.R;
import com.panshare.client.dto.LoginTO;
import com.panshare.client.dto.RegistryFormTO;
import com.panshare.client.dvo.UserDetailVO;
import com.panshare.client.dvo.UserVO;
import com.panshare.client.mapper.UserMapper;
import com.panshare.client.pojo.User;
import com.panshare.client.service.MailService;
import com.panshare.client.service.UserService;
import com.panshare.client.utils.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ImgUtils imgUtils;
    @Autowired
    private HttpServletRequest request;

    @Override
    public R getUserDetail(Integer userId) {
        UserDetailVO userDetailVO = this.userMapper.selectUserDetailById(userId);
        return R.ok().data("user", userDetailVO);
    }

    @Override
    public boolean getCode(String to) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email", to);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            throw new PanShareException("邮箱已注册！");
        }
        if (this.redisUtils.hasKey(Cache.EMAIL_SEND + to)) {
            throw new PanShareException("验证码已发送，请稍后重试！");
        }
        String code = this.mailService.sendMail(to);
        redisUtils.set(Cache.EMAIL_SEND + to, code, Cache.EMAIL_SEED_TIME, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public boolean userRegistry(RegistryFormTO registryFormTO) {
        String code = registryFormTO.getCode();
        String email = registryFormTO.getEmail();
        String key = Cache.EMAIL_SEND + email;
        String checkCode = this.redisUtils.get(key);
        boolean check = checkCode != null && checkCode.equals(code);
        if (!this.redisUtils.hasKey(key) || !check) {
            throw new PanShareException("邮箱验证码错误或已过期！");
        }
        User user = new User();
        String userIp = IPUtils.getIp(request);
        String userIpInfo = "";
        try {
            userIpInfo = IPUtils.visitor(userIp);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取注册用户IP失败：{}", e.getMessage());
        }
        user.setUserIp(userIp);
        user.setUserIpInfo(userIpInfo);
        user.setUserName(registryFormTO.getUserName());
        user.setUserPassword(Md5Utils.encodeByMd5(registryFormTO.getPassword()));
        user.setUserEmail(registryFormTO.getEmail());
        int insert = this.userMapper.insert(user);
        this.redisUtils.del(key);
        return insert > 0;
    }

    @Override
    public String login(LoginTO login) {
        String email = login.getEmail();
        String password = Md5Utils.encodeByMd5(login.getPassword());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email", email);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null || !user.getUserEmail().equals(email) || !user.getUserPassword().equals(password) || user.getUserDelete()) {
            throw new PanShareException("用户名或密码错误");
        }
        return JWTUtils.getToken("userId", user.getUserId());
    }

    @Override
    public R uploadAvatar(MultipartFile file) {
        String url = this.imgUtils.uploadImageQiniu(file, false);
        if (url == null || url.equals("")) {
            return R.error().message("上传失败！");
        }
        Integer userId = ThreadLocalUtil.get();
        User user = new User();
        user.setUserAvatar(url);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        int update = this.userMapper.update(user, queryWrapper);
        return R.ok().message(String.valueOf(update > 0));
    }

    @Override
    public R userInfo() {
        User user = userMapper.selectOne(getUser("user_id", "user_name", "user_avatar"));
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return R.ok().data("user", userVO);
    }

    @Override
    public R changePassword(String oldPwd, String newPwd) {
        User user = userMapper.selectOne(getUser("user_password", "user_id"));
        if (!Md5Utils.encodeByMd5(oldPwd).equals(user.getUserPassword())) {
            return R.error().message("旧密码不正确！");
        }
        user.setUserPassword(Md5Utils.encodeByMd5(newPwd));
        int update = this.userMapper.updateById(user);
        return R.ok().message(String.valueOf(update > 0));
    }

    @Override
    public R changeUserName(String userName) {
        User user = new User();
        user.setUserId(ThreadLocalUtil.get());
        user.setUserName(userName);
        int update = this.userMapper.updateById(user);
        return R.ok().message(String.valueOf(update > 0));
    }

    @Override
    public R changeEmail(String email, String code) {
        String key = Cache.EMAIL_SEND + email;
        String cacheCode = this.redisUtils.get(key);
        boolean verify = cacheCode != null && cacheCode.equals(code);
        if (!this.redisUtils.hasKey(key) || !verify) {
            return R.error().message("验证码错误");
        }
        User user = new User();
        user.setUserEmail(email);
        user.setUserId(ThreadLocalUtil.get());
        int update = this.userMapper.updateById(user);
        return R.ok().message(String.valueOf(update > 0));
    }

    private QueryWrapper<User> getUser(String... col) {
        Integer userId = ThreadLocalUtil.get();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return wrapper.eq("user_id", userId).select(col);
    }
}
