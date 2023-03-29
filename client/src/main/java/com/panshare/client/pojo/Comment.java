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

@ApiModel(value = "Comment对象", description = "")
@TableName("comment")
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 1;
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;
    @TableField("comment_post_id")
    @ApiModelProperty("评论帖子id")
    private Integer commentPostId;
    @TableField("comment_user_id")
    @ApiModelProperty("评论人")
    private Integer commentUserId;
    @TableField("comment_content")
    @ApiModelProperty("评论内容")
    private String commentContent;
    @TableField("comment_like")
    @ApiModelProperty("点赞量")
    private Integer commentLike;
    @TableField("comment_date")
    @ApiModelProperty("评论时间")
    private LocalDateTime commentDate;
    @TableField("comment_delete")
    @ApiModelProperty("逻辑删除，0表示未删除，1表示删除")
    private Boolean commentDelete;
}
