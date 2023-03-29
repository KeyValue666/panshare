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

@ApiModel(value = "User对象", description = "")
@TableName("user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1;
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    @TableField("user_name")
    @ApiModelProperty("用户名")
    private String userName;
    @TableField("user_email")
    @ApiModelProperty("用户邮箱(唯一)")
    private String userEmail;
    @TableField("user_password")
    @ApiModelProperty("密码")
    private String userPassword;
    @TableField("user_avatar")
    @ApiModelProperty("用户头像")
    private String userAvatar;
    @TableField("user_registry_time")
    @ApiModelProperty("注册日期")
    private LocalDateTime userRegistryTime;
    @TableField("user_delete")
    @ApiModelProperty("逻辑删除,0表示未删除，1表示删除")
    private Boolean userDelete;
    @TableField("user_ban")
    @ApiModelProperty("是否被禁言，0表示未禁言，1表示禁言")
    private Boolean userBan;
    @TableField("user_ip")
    @ApiModelProperty("用户ip")
    private String userIp;
    @TableField("user_ip_info")
    @ApiModelProperty("用户ip信息")
    private String userIpInfo;
}
