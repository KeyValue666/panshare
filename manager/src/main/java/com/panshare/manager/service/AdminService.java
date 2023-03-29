package com.panshare.manager.service;

import com.panshare.manager.common.R;
import com.panshare.manager.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
public interface AdminService extends IService<Admin> {

    /**
     * 登录
     * @param user
     * @return
     */
    R login(Admin user);
}
