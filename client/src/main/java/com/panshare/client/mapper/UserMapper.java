package com.panshare.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panshare.client.dvo.UserDetailVO;
import com.panshare.client.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    UserDetailVO selectUserDetailById(@Param("userId") Integer userId);
}
