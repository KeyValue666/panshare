package com.panshare.client.controller;

import com.panshare.client.common.R;
import com.panshare.client.service.LikeService;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping({"/like/"})
@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping({"/comment"})
    public R likeComment(@RequestParam("commentId") @NotNull Integer commentId) {
        boolean like = this.likeService.likeComment(commentId);
        return R.ok().data("like", like);
    }

    @PostMapping({"post"})
    public R likePost(@RequestParam("postId") @NotNull Integer postId) {
        boolean like = this.likeService.likePost(postId);
        return R.ok().data("like", like);
    }
}
