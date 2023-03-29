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

@ApiModel(value = "Report对象", description = "")
@TableName("report")
@Data
public class Report implements Serializable {
    private static final long serialVersionUID = 1;
    @ApiModelProperty("举报id")
    @TableId(value = "report_id", type = IdType.AUTO)
    private Integer reportId;
    @TableField("report_from_id")
    @ApiModelProperty("内容来源id")
    private Integer reportFromId;
    @TableField("report_type")
    @ApiModelProperty("举报类型,0表示为帖子，1表示评论")
    private Integer reportType;
    @TableField("report_content_type")
    @ApiModelProperty("举报内容类型")
    private Integer reportContentType;
    @TableField("report_time")
    @ApiModelProperty("举报日期")
    private LocalDateTime reportTime;
    @TableField("report_user_id")
    @ApiModelProperty("举报人")
    private Integer reportUserId;
    @TableField("report_is_finish")
    @ApiModelProperty("是否完成，0表示未完成，1表示完成")
    private Boolean reportIsFinish;
    @TableField("report_info")
    @ApiModelProperty("举报详细信息")
    private String reportInfo;
}
