package com.panshare.manager.service;

import com.panshare.manager.common.R;
import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
public interface UserService extends IService<User> {

    /**
     * 根据条件查询用户
     * @param condition
     * @return
     */
    R listUserByCondition(QueryCondition condition);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    boolean deleteUser(Integer userId);

    /**
     * 禁言用户
     * @param userId
     * @param userStatue
     * @return
     */
    boolean banUser(Integer userId, Boolean userState);
}
