package com.panshare.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panshare.client.common.R;
import com.panshare.client.dto.PostDTO;
import com.panshare.client.dvo.PostContentVO;
import com.panshare.client.dvo.PostDetailVO;
import com.panshare.client.dvo.PostVO;
import com.panshare.client.mapper.PostMapper;
import com.panshare.client.mapper.UserMapper;
import com.panshare.client.pojo.Post;
import com.panshare.client.pojo.User;
import com.panshare.client.service.LikeService;
import com.panshare.client.service.PostService;
import com.panshare.client.utils.RedisUtils;
import com.panshare.client.utils.ThreadLocalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static javafx.beans.binding.Bindings.select;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private LikeService likeService;

    @Override
    public R listPost(Integer tagId, Integer sortWays, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<PostVO> list = this.postMapper.getPostByTagId(tagId, sortWays);
        Map<String, Object> map = pagePack(list);
        return R.ok().data(map);
    }

    @Override
    public R getPostById(Integer postId) {
        PostDetailVO postDetailVo = this.postMapper.getPostById(postId);
        return R.ok().data("postDetail", postDetailVo);
    }

    @Override
    public R getPostContent(Integer postId) {
        PostContentVO postContentVO = this.postMapper.getPostContentById(postId);
        if (postContentVO.getUserId() == null) {
            postContentVO.setUserId(-1);
            postContentVO.setUserName("Admin");
        }
        this.likeService.singlePostInit(postContentVO);
        return R.ok().data("post", postContentVO);
    }

    @Override // com.hjs.panshare.service.PostService
    public R getUserPost(Integer userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page.intValue(), pageSize.intValue());
        List<PostVO> list = this.postMapper.queryPostByUserId(userId);
        Map<String, Object> map = pagePack(list);
        return R.ok().data(map);
    }

    @Override
    public R upload(PostDTO postDTO) {
        Integer userId = ThreadLocalUtil.get();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).select("user_ban");
        User user = userMapper.selectOne(queryWrapper);
        if (user.getUserBan()) {
            return R.error().message("你已被系统禁言，如有异议请联系管理员！");
        }
        postDTO.setUserId(userId);
        this.postMapper.savePost(postDTO);
        return R.ok().data("postId", postDTO.getPostId());
    }

    @Override // com.hjs.panshare.service.PostService
    public R deleteByUser(Integer postId) {
        Integer userId = ThreadLocalUtil.get();
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId).ne("post_delete", 1).select("post_id", "post_user_id");
        Post post = postMapper.selectOne(queryWrapper);
        Integer postUserId = post.getPostUserId();
        if (postUserId == null || !postUserId.equals(userId)) {
            return R.error().message("你没有权限删除该帖子");
        }
        post.setPostDelete(true);
        int update = this.postMapper.updateById(post);
        return R.ok().message(String.valueOf(update));
    }

    public Map<String, Object> pagePack(List<PostVO> list) {
        PageInfo<PostVO> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        List<PostVO> postVo = pageInfo.getList();
        map.put("posts", postVo);
        map.put("total", pageInfo.getTotal());
        map.put("hasMore", pageInfo.isHasNextPage());
        map.put("nextPage", pageInfo.getNextPage());
        return map;
    }

    public <T> T getCache(String key, Class<T> cls) {
        return (T) this.redisUtils.get(key, cls);
    }
}
