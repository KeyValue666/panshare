package com.panshare.client.service;

import com.panshare.client.pojo.Post;

import java.util.List;
import java.util.Map;

/**
 * @Author Key&Value
 * @Date 2023/3/12 17:31
 * @Version 1.0
 */
public interface SearchService {
    /**
     * 搜索帖子
     *
     * @param key 标签
     * @param page 页码
     * @param pageSize 每页展示数量
     * @return
     */
    Map<String,Object> searchPosts(String key, Integer page, Integer pageSize);
}
