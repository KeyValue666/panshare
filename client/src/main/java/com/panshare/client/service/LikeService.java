package com.panshare.client.service;

import com.panshare.client.dvo.PostCommentVO;
import com.panshare.client.dvo.PostContentVO;
import java.util.List;

/* loaded from: LikeService.class */
public interface LikeService {
    void likeInitComment(List<PostCommentVO> list);

    void singlePostInit(PostContentVO postContentVO);

    boolean likeComment(Integer commentId);

    boolean likePost(Integer postId);
}
