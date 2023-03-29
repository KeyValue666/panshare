package com.panshare.manager.vo;

import lombok.Data;

/**
 * @Author Key&Value
 * @Date 2023/2/21 8:43
 * @Version 1.0
 */
@Data
public class PostVO {
    private Integer postId;
    private String postTitle;
    private String postDate;
    private Boolean postState;
    private String tagIcon;
    private String tagName;
    private Integer postLike;
}
