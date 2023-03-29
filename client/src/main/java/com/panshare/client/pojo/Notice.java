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

@ApiModel(value = "Notice对象", description = "")
@TableName("notice")
@Data
public class Notice implements Serializable {
    private static final long serialVersionUID = 1;
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;
    @TableField("notice_title")
    @ApiModelProperty("公告标题")
    private String noticeTitle;
    @TableField("notice_content")
    @ApiModelProperty("公告内容")
    private String noticeContent;
    @TableField("notice_time")
    @ApiModelProperty("公告时间")
    private LocalDateTime noticeTime;
    @TableField("notice_delete")
    @ApiModelProperty("是否逻辑删除，0表示未删除，1表示删除")
    private Boolean noticeDelete;
    @TableField("notice_is_top")
    @ApiModelProperty("是否置顶，0表示不置顶，1表示置顶")
    private Boolean noticeIsTop;
}
