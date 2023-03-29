package com.panshare.manager.controller;


import com.panshare.manager.common.R;
import com.panshare.manager.pojo.Admin;
import com.panshare.manager.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public R login(@RequestBody Admin admin){
        return adminService.login(admin);
    }
}

