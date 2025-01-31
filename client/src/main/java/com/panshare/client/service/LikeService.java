package com.panshare.client.service;

import com.panshare.client.dvo.PostCommentVO;
import com.panshare.client.dvo.PostContentVO;
import java.util.List;

public interface LikeService {
    void likeInitComment(List<PostCommentVO> list);

    void singlePostInit(PostContentVO postContentVO);

    boolean likeComment(Integer commentId);

    boolean likePost(Integer postId);
}
