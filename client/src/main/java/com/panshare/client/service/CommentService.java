package com.panshare.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panshare.client.common.R;
import com.panshare.client.dto.CommentDTO;
import com.panshare.client.pojo.Comment;

public interface CommentService extends IService<Comment> {
    R commentList(Integer postId, Integer page, Integer pageSize);

    R listUserComment(Integer userId, Integer page, Integer pageSize);

    R publishComment(CommentDTO commentDTO);

    R deleteCommentFromUser(Integer commentId);
}
