package com.panshare.client.service.impl;

import com.panshare.client.common.Cache;
import com.panshare.client.dvo.PostCommentVO;
import com.panshare.client.dvo.PostContentVO;
import com.panshare.client.service.LikeService;
import com.panshare.client.utils.JWTUtils;
import com.panshare.client.utils.RedisUtils;
import com.panshare.client.utils.ThreadLocalUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void likeInitComment(List<PostCommentVO> list) {
        Integer userId = getUserId();
        for (PostCommentVO commentVO : list) {
            String key = Cache.LIKE_COMMENT + commentVO.getCommentId();
            Long totalLike = this.redisUtils.sLen(key);
            commentVO.setCommentLike(totalLike);
            if (userId != null && this.redisUtils.isMember(key, String.valueOf(userId))) {
                commentVO.setIsLike(true);
            }
        }
    }

    @Override
    public boolean likeComment(Integer commentId) {
        String userId = ThreadLocalUtil.get() + "";
        String key = Cache.LIKE_COMMENT + commentId;
        if (!this.redisUtils.hasKey(key) || !this.redisUtils.isMember(key, userId)) {
            this.redisUtils.sAdd(key, userId);
            return true;
        }
        this.redisUtils.sDel(key, userId);
        return false;
    }

    @Override
    public boolean likePost(Integer postId) {
        String userId = ThreadLocalUtil.get() + "";
        String key = Cache.LIKE_POST + postId;
        if (!this.redisUtils.hasKey(key) || !this.redisUtils.isMember(key, userId)) {
            this.redisUtils.sAdd(key, userId);
            return true;
        }
        this.redisUtils.sDel(key, userId);
        return false;
    }

    @Override
    public void singlePostInit(PostContentVO postContentVO) {
        Integer userId = getUserId();
        String key = Cache.LIKE_POST + postContentVO.getPostId();
        Long totalLike = this.redisUtils.sLen(key);
        postContentVO.setPostLike(totalLike);
        if (userId != null && this.redisUtils.isMember(key, String.valueOf(userId))) {
            postContentVO.setIsLike(true);
        }
    }

    private Integer getUserId() {
        String token = this.request.getHeader("token");
        if (token == null) {
            return null;
        }
        try {
            return JWTUtils.verify(token).getClaim("userId").asInt();
        } catch (Exception e) {
            log.info("用户未登录{}", e.getMessage());
            return null;
        }
    }
}
