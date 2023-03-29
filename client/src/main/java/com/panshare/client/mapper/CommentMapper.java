package com.panshare.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panshare.client.dto.CommentDTO;
import com.panshare.client.dvo.PostCommentVO;
import com.panshare.client.pojo.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    List<PostCommentVO> getPostCommentById(@Param("postId") Integer postId);

    List<PostCommentVO> getUserComment(@Param("userId") Integer userId);

    boolean saveComment(@Param("commentDTO") CommentDTO commentDTO);
}
