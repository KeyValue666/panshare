package com.panshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panshare.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author Key&Value
 * @Date 2023/3/10 21:21
 * @Version 1.0
 */
@Mapper
@Repository
public interface PostMapper extends BaseMapper<Post> {
}
