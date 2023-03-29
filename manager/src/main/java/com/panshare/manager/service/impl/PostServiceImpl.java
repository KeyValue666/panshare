package com.panshare.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panshare.manager.common.R;
import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.pojo.Post;
import com.panshare.manager.mapper.PostMapper;
import com.panshare.manager.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panshare.manager.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@Service
@Slf4j
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public R queryByCondition(QueryCondition queryCondition) {
        PageHelper.startPage(queryCondition.getPage(), queryCondition.getPageSize());
        List<PostVO> list = postMapper.queryByCondition(queryCondition);
        PageInfo<PostVO> pageInfo = new PageInfo<>(list);
        List<PostVO> post = pageInfo.getList();
        long total = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("post", post);
        map.put("total", total);
        return R.ok().data(map);
    }

    @Override
    public R setTop(Integer postId, Boolean isTop) {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", postId);
        Post post = new Post();
        post.setPostId(postId);
        post.setPostIsTop(isTop);
        int update = postMapper.update(post, wrapper);
        return R.ok().data("flag", update > 0);
    }

    @Override
    public boolean deleteOne(Integer postId) {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", postId);
        Post post = new Post();
        post.setPostId(postId);
        post.setPostDelete(true);
        int update = postMapper.update(post, wrapper);
        return update > 0;
    }

    @Override
    public R deleteMore(List<Integer> ids) {
        boolean flag = postMapper.deleteBatch(ids);
        return R.ok().data("flag",flag);
    }
}
