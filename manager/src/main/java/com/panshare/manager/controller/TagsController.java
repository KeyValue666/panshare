package com.panshare.manager.controller;


import com.panshare.manager.common.R;
import com.panshare.manager.dto.TagDTO;
import com.panshare.manager.pojo.Tags;
import com.panshare.manager.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping("/tags")
@Validated
public class TagsController {
    @Autowired
    private TagsService tagsService;
    @GetMapping("/")
    public R listAll(){
        List<Tags> list = tagsService.list();
        return R.ok().data("tags",list);
    }
    @PostMapping("/save")
    public R saveTag(@RequestBody @Valid TagDTO tagDTO){
        return tagsService.saveTag(tagDTO);
    }

}

