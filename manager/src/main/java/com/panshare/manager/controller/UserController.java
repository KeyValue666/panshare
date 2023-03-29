package com.panshare.manager.controller;


import com.panshare.manager.common.R;
import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public R listUser(@RequestBody @Valid QueryCondition condition){
        return userService.listUserByCondition(condition);
    }
    @DeleteMapping("/delete")
    public R deleteUser(@RequestParam("userId") Integer userId){
        boolean update = userService.deleteUser(userId);
        return R.ok().data("flag",update);
    }
    @PostMapping("/ban")
    public R banUser(@RequestParam("userId")@NotNull Integer userId,@RequestParam("userState") Boolean userState){
        boolean update = userService.banUser(userId,userState);
        return R.ok().data("flag",update);
    }
}

