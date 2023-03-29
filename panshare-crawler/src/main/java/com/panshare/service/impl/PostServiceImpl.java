package com.panshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panshare.mapper.PostMapper;
import com.panshare.pojo.Post;
import com.panshare.service.PostService;
import org.springframework.stereotype.Service;

/**
 * @Author Key&Value
 * @Date 2023/3/10 21:25
 * @Version 1.0
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
}
