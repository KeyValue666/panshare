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
@ApiModel(value="Report对象", description="")
public class Report extends Model<Report> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "举报id")
      @TableId(value = "report_id", type = IdType.AUTO)
    private Integer reportId;

    @ApiModelProperty(value = "内容来源id")
    private Integer reportFromId;

    @ApiModelProperty(value = "举报类型,0表示为帖子，1表示评论")
    private Integer reportType;

    @ApiModelProperty(value = "举报内容类型")
    private Integer reportContentType;

    @ApiModelProperty(value = "举报日期")
    private LocalDateTime reportTime;

    @ApiModelProperty(value = "举报人")
    private Integer reportUserId;

    @ApiModelProperty(value = "是否完成，0表示未完成，1表示完成")
    private Boolean reportIsFinish;

    @ApiModelProperty(value = "举报详细信息")
    private String reportInfo;


    @Override
    protected Serializable pkVal() {
        return this.reportId;
    }

}
