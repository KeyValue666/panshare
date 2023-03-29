package com.panshare.client.dvo;

import lombok.Data;

@Data

public class PostVO {
    private Integer postId;
    private Integer postUserId;
    private String postTitle;
    private String postTime;
    private Integer postCommentCount;
    private Boolean postIsTop;
    private String userAvatar;
    private String userName;
    private String tagName;
    private String tagIcon;
    private String tagColor;
}
