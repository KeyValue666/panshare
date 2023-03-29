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
@ApiModel(value="Tags对象", description="")
public class Tags extends Model<Tags> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "tag_id", type = IdType.AUTO)
    private Integer tagId;

    @ApiModelProperty(value = "标签名")
    private String tagName;

    @ApiModelProperty(value = "标签颜色")
    private String tagColor;

    @ApiModelProperty(value = "标签图标")
    private String tagIcon;

    @ApiModelProperty(value = "逻辑删除，0未删除，1表示删除")
    private Boolean tagDelete;

    @ApiModelProperty(value = "标签创建时间")
    private LocalDateTime tagCreateTime;

    @ApiModelProperty(value = "用户是否可以选择，0表示公开，1表示不公开")
    private Boolean tagIsPublic;


    @Override
    protected Serializable pkVal() {
        return this.tagId;
    }

}
