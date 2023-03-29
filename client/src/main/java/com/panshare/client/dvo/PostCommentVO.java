package com.panshare.client.dvo;

import lombok.Data;

@Data
public class PostCommentVO {
    private Integer commentId;
    private Long commentLike;
    private Boolean isLike;
    private String commentContent;
    private String commentTime;
    private Integer userId;
    private String userAvatar;
    private String userName;
    private Integer postId;
}
