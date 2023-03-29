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
@ApiModel(value="Post对象", description="")
public class Post extends Model<Post> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    @ApiModelProperty(value = "发表用户")
    private Integer postUserId;

    @ApiModelProperty(value = "帖子标题")
    private String postTitle;

    @ApiModelProperty(value = "帖子内容")
    private String postContent;

    @ApiModelProperty(value = "帖子标签")
    private Integer postTagId;

    @ApiModelProperty(value = "发表日期")
    private LocalDateTime postTime;

    @ApiModelProperty(value = "点赞量")
    private Integer postLike;

    @ApiModelProperty(value = "逻辑删除，0表示未删除，1表示删除")
    private Boolean postDelete;

    @ApiModelProperty(value = "是否置顶，0表示不置顶，1表示置顶")
    private Boolean postIsTop;


    @Override
    protected Serializable pkVal() {
        return this.postId;
    }

}
