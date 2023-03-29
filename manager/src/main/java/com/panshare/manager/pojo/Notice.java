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
@ApiModel(value="Notice对象", description="")
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    @ApiModelProperty(value = "公告标题")
    private String noticeTitle;

    @ApiModelProperty(value = "公告内容")
    private String noticeContent;

    @ApiModelProperty(value = "公告时间")
    private LocalDateTime noticeTime;

    @ApiModelProperty(value = "是否逻辑删除，0表示未删除，1表示删除")
    private Boolean noticeDelete;

    @ApiModelProperty(value = "是否置顶，0表示不置顶，1表示置顶")
    private Boolean noticeIsTop;


    @Override
    protected Serializable pkVal() {
        return this.noticeId;
    }

}
