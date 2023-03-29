package com.panshare.client.controller;

import com.panshare.client.common.R;
import com.panshare.client.dto.CommentDTO;
import com.panshare.client.service.CommentService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping({"/comment/"})
@RestController
@Validated
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping({"{postId}"})
    public R commentList(@PathVariable @Positive Integer postId, @RequestParam("page") @Positive Integer page, @RequestParam("pageSize") @Positive Integer pageSize) {
        return this.commentService.commentList(postId, page, pageSize);
    }

    @GetMapping({"user/{userId}"})
    public R userComment(@PathVariable Integer userId, @RequestParam("page") @NotNull Integer page, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return this.commentService.listUserComment(userId, page, pageSize);
    }

    @PostMapping({"publish"})
    public R publishComment(@Valid @RequestBody CommentDTO commentDTO) {
        return this.commentService.publishComment(commentDTO);
    }

    @PostMapping({"delete"})
    public R deleteCommentByUser(@RequestParam("commentId") @NotNull Integer commentId) {
        return this.commentService.deleteCommentFromUser(commentId);
    }
}
