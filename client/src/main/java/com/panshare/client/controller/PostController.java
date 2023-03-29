package com.panshare.client.controller;

import com.panshare.client.common.R;
import com.panshare.client.dto.PostDTO;
import com.panshare.client.pojo.Post;
import com.panshare.client.service.PostService;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.panshare.client.service.SearchService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping({"/post/"})
@RestController
@Validated
@Slf4j
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private SearchService searchService;

    @GetMapping({"{tagId}"})
    public R getPost(@PathVariable("tagId") Integer tagId, @RequestParam(value = "sortValue", defaultValue = "0") @Max(3) @Min(0) Integer sortWays, @RequestParam("page") @NotNull(message = "page不能为空") @Positive Integer page, @RequestParam("pageSize") @Max(value = 8, message = "每页最多8条数据") @Positive Integer pageSize) {
        return this.postService.listPost(tagId, sortWays, page, pageSize);
    }

    @GetMapping({"detail"})
    public R postDetail(@RequestParam("postId") @Positive Integer postId) {
        return this.postService.getPostById(postId);
    }

    @GetMapping({"content"})
    public R postContent(@RequestParam("postId") @Positive Integer postId) {
        return this.postService.getPostContent(postId);
    }

    @GetMapping({"user/{userId}"})
    public R userPost(@PathVariable Integer userId, @RequestParam("page") @NotNull Integer page, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return this.postService.getUserPost(userId, page, pageSize);
    }

    @PostMapping({"upload"})
    public R uploadPost(@Valid @RequestBody PostDTO postDTO) {
        return this.postService.upload(postDTO);
    }

    @PostMapping({"delete"})
    public R deleteByUser(@RequestParam("postId") @NotNull Integer postId) {
        return this.postService.deleteByUser(postId);
    }

    @GetMapping("search")
    public R search(@NotNull @RequestParam("key") String key,
                    @NotNull @RequestParam("page") Integer page,
                    @NotNull @RequestParam("pageSize") Integer pageSize) {
        Map<String, Object> map = searchService.searchPosts(key, page, pageSize);
        return R.ok().data(map);
    }
}
