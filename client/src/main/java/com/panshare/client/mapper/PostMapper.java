package com.panshare.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panshare.client.dto.PostDTO;
import com.panshare.client.dvo.PostContentVO;
import com.panshare.client.dvo.PostDetailVO;
import com.panshare.client.dvo.PostVO;
import com.panshare.client.pojo.Post;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostMapper extends BaseMapper<Post> {
    List<PostVO> getPostByTagId(@Param("tagId") Integer tagId, @Param("sortWays") Integer sortWays);

    PostDetailVO getPostById(@Param("postId") Integer postId);

    PostContentVO getPostContentById(@Param("postId") Integer postId);

    List<PostVO> queryPostByUserId(@Param("userId") Integer userId);

    boolean savePost(@Param("postDTO") PostDTO postDTO);
}
