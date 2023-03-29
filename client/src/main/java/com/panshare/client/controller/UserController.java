package com.panshare.client.controller;

import com.panshare.client.common.R;
import com.panshare.client.dto.LoginTO;
import com.panshare.client.dto.RegistryFormTO;
import com.panshare.client.service.UserService;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping({"/user/"})
@RestController
@Validated
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping({"login"})
    public R login(@Valid @RequestBody LoginTO login) {
        String token = this.userService.login(login);
        return R.ok().data("token", token);
    }

    @PostMapping({"registry"})
    public R registry(@Valid @RequestBody RegistryFormTO registryFormTO) {
        log.info("{}", registryFormTO);
        boolean res = this.userService.userRegistry(registryFormTO);
        return R.ok().data("login", res);
    }

    @PostMapping({"upload"})
    public R updateAvatar(@RequestPart("file") MultipartFile file) {
        return this.userService.uploadAvatar(file);
    }

    @PostMapping({"info"})
    public R getUserInfo() {
        return this.userService.userInfo();
    }

    @GetMapping({"userDetail/{userId}"})
    public R userDetail(@PathVariable @NotNull(message = "请求参数不合法") Integer userId) {
        return this.userService.getUserDetail(userId);
    }

    @PostMapping({"/changePwd"})
    public R changePassword(@RequestParam("oldPwd") @NotBlank String oldPwd, @RequestParam("newPwd") @NotBlank String newPwd) {
        return this.userService.changePassword(oldPwd, newPwd);
    }

    @PostMapping({"/changeUserName"})
    public R changeUserName(@RequestParam("userName") @NotBlank(message = "用户名不能为空") String userName) {
        return this.userService.changeUserName(userName);
    }

    @PostMapping({"/changeEmail"})
    public R changeEmail(@RequestParam("email") @Email String email, @RequestParam("code") @NotBlank(message = "验证码不能为空") String code) {
        return this.userService.changeEmail(email, code);
    }

    @PostMapping({"code"})
    public R veryCode(@RequestParam("email") @Email String to) {
        this.userService.getCode(to);
        return R.ok().message("邮箱已发送，请注意查收！");
    }
}
