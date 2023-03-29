package com.panshare.client.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.panshare.client.common.Cache;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(value = "Tags对象", description = "")
@TableName(Cache.TAG_CACHE)
@Data
public class Tags implements Serializable {
    private static final long serialVersionUID = 1;
    @TableId(value = "tag_id", type = IdType.AUTO)
    private Integer tagId;
    @TableField("tag_name")
    @ApiModelProperty("标签名")
    private String tagName;
    @TableField("tag_color")
    @ApiModelProperty("标签颜色")
    private String tagColor;
    @TableField("tag_icon")
    @ApiModelProperty("标签图标")
    private String tagIcon;
    @TableField("tag_delete")
    @ApiModelProperty("逻辑删除，0未删除，1表示删除")
    private Boolean tagDelete;
    @TableField("tag_create_time")
    @ApiModelProperty("标签创建时间")
    private LocalDateTime tagCreateTime;
    @TableField("tag_is_public")
    @ApiModelProperty("用户是否可以选择，0表示公开，1表示不公开")
    private Boolean tagIsPublic;
}
