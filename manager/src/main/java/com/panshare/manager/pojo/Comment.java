package com.panshare.manager.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Comment对象", description="")
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    @ApiModelProperty(value = "评论帖子id")
    private Integer commentPostId;

    @ApiModelProperty(value = "评论人")
    private Integer commentUserId;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @ApiModelProperty(value = "点赞量")
    private Integer commentLike;

    @ApiModelProperty(value = "评论时间")
    private LocalDateTime commentDate;

    @ApiModelProperty(value = "逻辑删除，0表示未删除，1表示删除")
    private Boolean commentDelete;


    @Override
    protected Serializable pkVal() {
        return this.commentId;
    }

}
