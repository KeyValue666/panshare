package com.panshare.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panshare.client.common.R;
import com.panshare.client.dto.PostDTO;
import com.panshare.client.pojo.Post;

public interface PostService extends IService<Post> {
    R listPost(Integer tagId, Integer sortWays, Integer page, Integer pageSize);

    R getPostById(Integer postId);

    R getPostContent(Integer postId);

    R getUserPost(Integer userId, Integer page, Integer pageSize);

    R upload(PostDTO postDTO);

    R deleteByUser(Integer postId);
}
