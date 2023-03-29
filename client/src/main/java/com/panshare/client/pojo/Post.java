package com.panshare.client.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(value = "Post对象", description = "")
@TableName("post")
@Data
public class Post implements Serializable {
    private static final long serialVersionUID = 1;
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;
    @TableField("post_user_id")
    @ApiModelProperty("发表用户")
    private Integer postUserId;
    @TableField("post_title")
    @ApiModelProperty("帖子标题")
    private String postTitle;
    @TableField("post_content")
    @ApiModelProperty("帖子内容")
    private String postContent;
    @TableField("post_tag_id")
    @ApiModelProperty("帖子标签")
    private Integer postTagId;
    @TableField("post_time")
    @ApiModelProperty("发表日期")
    private LocalDateTime postTime;
    @TableField("post_like")
    @ApiModelProperty("点赞量")
    private Integer postLike;
    @TableField("post_delete")
    @ApiModelProperty("逻辑删除，0表示未删除，1表示删除")
    private Boolean postDelete;
    @TableField("post_is_top")
    @ApiModelProperty("是否置顶，0表示不置顶，1表示置顶")
    private Boolean postIsTop;
}
