package com.panshare.manager.controller;


import com.panshare.manager.common.R;
import com.panshare.manager.pojo.Icon;
import com.panshare.manager.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@RestController
@RequestMapping("/icon")
public class IconController {
    @Autowired
    private IconService iconService;
    @GetMapping("/")
    public R listAll(){
        List<Icon> icons = iconService.list();
        return R.ok().data("icons",icons);
    }
}

