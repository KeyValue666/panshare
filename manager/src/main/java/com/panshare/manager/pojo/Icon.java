package com.panshare.manager.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Icon对象", description="")
public class Icon extends Model<Icon> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "icon_id", type = IdType.AUTO)
    private Integer iconId;

    @ApiModelProperty(value = "图标的css类")
    private String iconClass;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean iconDelete;


    @Override
    protected Serializable pkVal() {
        return this.iconId;
    }

}
