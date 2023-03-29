package com.panshare.manager.controller;


import com.panshare.manager.common.R;
import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@RequestMapping("/post")
@Validated
@Slf4j
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/")
    public R queryPostByCondition(@RequestBody @Valid QueryCondition queryCondition){
        return postService.queryByCondition(queryCondition);
    }
    @PostMapping("/top")
    public R setPostToTop(@RequestParam("postId") @NotNull Integer postId,@RequestParam("isTop") @NotNull Boolean isTop){
        return postService.setTop(postId,isTop);
    }
    @DeleteMapping("/single")
    public R deleteOne(@RequestParam("postId") @NotNull Integer postId){
        boolean update = postService.deleteOne(postId);
        return R.ok().data("flag",update);
    }
    @DeleteMapping("/more")
    public R deleteMore(@RequestBody @Size(min = 1) List<Integer> ids){
        return postService.deleteMore(ids);
    }
}

