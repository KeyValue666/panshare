package com.panshare.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author Key&Value
 * @Date 2023/3/9 20:23
 * @Version 1.0
 */
@Data
@TableName("post")
public class Post {
    private Integer postId;
    private Integer postUserId;
    private String postTitle;
    private String postContent;
    private Integer postTagId;
}
