package com.panshare.client.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class PostDTO {
    private Integer userId;
    @NotBlank(message = "文章标题不能为空")
    private String postTitle;
    @NotBlank(message = "文章内容不能为空")
    private String postContent;
    @NotNull(message = "帖子标签不能为空")
    private Integer postTagId;
    private Integer postId;
}
