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
@ApiModel(value="User对象", description="")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户邮箱(唯一)")
    private String userEmail;

    @ApiModelProperty(value = "密码")
    private String userPassword;

    @ApiModelProperty(value = "用户头像")
    private String userAvatar;

    @ApiModelProperty(value = "注册日期")
    private LocalDateTime userRegistryTime;

    @ApiModelProperty(value = "逻辑删除,0表示未删除，1表示删除")
    private Boolean userDelete;

    @ApiModelProperty(value = "是否被禁言，0表示未禁言，1表示禁言")
    private Boolean userBan;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
