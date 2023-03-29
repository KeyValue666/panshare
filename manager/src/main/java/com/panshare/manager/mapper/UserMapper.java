package com.panshare.manager.mapper;

import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panshare.manager.vo.UserVO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据条件查询用户
     * @param queryCondition
     * @return
     */
    List<UserVO> queryByCondition(QueryCondition queryCondition);
}
