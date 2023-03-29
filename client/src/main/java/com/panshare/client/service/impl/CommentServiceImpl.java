package com.panshare.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panshare.client.common.R;
import com.panshare.client.dto.CommentDTO;
import com.panshare.client.dvo.PostCommentVO;
import com.panshare.client.mapper.CommentMapper;
import com.panshare.client.mapper.UserMapper;
import com.panshare.client.pojo.Comment;
import com.panshare.client.pojo.User;
import com.panshare.client.service.CommentService;
import com.panshare.client.service.LikeService;
import com.panshare.client.utils.Html2Text;
import com.panshare.client.utils.ThreadLocalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static javafx.beans.binding.Bindings.select;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LikeService likeService;

    @Override
    public R commentList(Integer postId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<PostCommentVO> list = commentMapper.getPostCommentById(postId);
        Map<String, Object> map = commentPage(list);
        return R.ok().data(map);
    }

    @Override
    public R listUserComment(Integer userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<PostCommentVO> list = commentMapper.getUserComment(userId);
        for (PostCommentVO commentVO : list) {
            String content = Html2Text.getContent(commentVO.getCommentContent());
            commentVO.setCommentContent(content);
        }
        Map<String, Object> map = commentPage(list);
        return R.ok().data(map);
    }

    @Override
    public R publishComment(CommentDTO commentDTO) {
        Integer userId = ThreadLocalUtil.get();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).select("user_ban");
        User user = userMapper.selectOne(queryWrapper);
        if (user.getUserBan()) {
            return R.error().message("你已被系统禁言,请联系管理员！");
        }
        commentDTO.setUserId(userId);
        boolean flag = commentMapper.saveComment(commentDTO);
        return R.ok().message(String.valueOf(flag));
    }

    @Override
    public R deleteCommentFromUser(Integer commentId) {
        Integer userId = ThreadLocalUtil.get();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("comment_id", commentId).ne("comment_delete", 1).select("comment_id", "comment_user_id");
        Comment comment = commentMapper.selectOne(queryWrapper);
        Integer commentUserId = comment.getCommentUserId();
        if (commentUserId == null || !commentUserId.equals(userId)) {
            return R.error().message("你没有权限删除别人的评论");
        }
        comment.setCommentDelete(true);
        int update = commentMapper.updateById(comment);
        return R.ok().message(String.valueOf(update));
    }

    private Map<String, Object> commentPage(List<PostCommentVO> list) {
        PageInfo<PostCommentVO> pageInfo = new PageInfo<>(list);
        List<PostCommentVO> comment = pageInfo.getList();
        long total = pageInfo.getTotal();
        boolean hasMore = pageInfo.isHasNextPage();
        int nextPage = pageInfo.getNextPage();
        likeService.likeInitComment(comment);
        Map<String, Object> map = new HashMap<>();
        map.put("comment", comment);
        map.put("hasMore", hasMore);
        map.put("total", total);
        map.put("nextPage", nextPage);
        return map;
    }
}
