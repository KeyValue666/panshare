package com.panshare.client.dvo;

import lombok.Data;

@Data
public class PostContentVO {
    private Integer userId;
    private Integer postId;
    private String userName;
    private String userAvatar;
    private String postContent;
    private String postTime;
    private Boolean isLike;
    private Long postLike;
}
