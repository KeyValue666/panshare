package com.panshare.manager.vo;

import lombok.Data;

/**
 * @Author Key&Value
 * @Date 2023/2/21 10:23
 * @Version 1.0
 */
@Data
public class UserVO {
    private Integer userId;
    private String userName;
    private String userAvatar;
    private String userEmail;
    private String userRegistry;
    private Boolean userState; //用户状态
    private Boolean userDelete; //用户是否删除
    private String userIp;
    private String userIpInfo;
}
