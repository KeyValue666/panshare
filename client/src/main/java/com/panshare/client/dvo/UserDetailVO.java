package com.panshare.client.dvo;

import lombok.Data;

@Data
public class UserDetailVO {
    private Integer userId;
    private String userName;
    private String userAvatar;
    private String userRegisterDate;
    private Integer userReply;
    private Integer userPost;
}
