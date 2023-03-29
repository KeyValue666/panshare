package com.panshare.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panshare.manager.common.R;
import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.pojo.User;
import com.panshare.manager.mapper.UserMapper;
import com.panshare.manager.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panshare.manager.vo.UserVO;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public R listUserByCondition(QueryCondition condition) {
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<UserVO> list = userMapper.queryByCondition(condition);
        PageInfo<UserVO> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("user", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return R.ok().data(map);
    }

    @Override
    public boolean deleteUser(Integer userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        User user = new User();
        user.setUserDelete(true);
        int update = userMapper.update(user, wrapper);
        return update > 0;
    }

    @Override
    public boolean banUser(Integer userId, Boolean userState) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        User user = new User();
        user.setUserBan(userState);
        int update = userMapper.update(user, wrapper);
        return update > 0;
    }
}
