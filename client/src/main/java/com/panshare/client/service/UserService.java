package com.panshare.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panshare.client.common.R;
import com.panshare.client.dto.LoginTO;
import com.panshare.client.dto.RegistryFormTO;
import com.panshare.client.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    R getUserDetail(Integer userId);

    boolean getCode(String to);

    boolean userRegistry(RegistryFormTO registryFormTO);

    String login(LoginTO login);

    R uploadAvatar(MultipartFile file);

    R userInfo();

    R changePassword(String oldPwd, String newPwd);

    R changeUserName(String userName);

    R changeEmail(String email, String code);
}
