package com.panshare.client.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDTO {
    private Integer userId;
    @NotBlank(message = "评论内容不能为空")
    private String commentContent;
    @NotNull(message = "帖子id不能为空")
    private Integer postId;
}
