package com.panshare.manager.vo;

import lombok.Data;

/**
 * @Author Key&Value
 * @Date 2023/2/21 14:06
 * @Version 1.0
 */
@Data
public class ReportVO {
    private Integer reportId;
    private Integer reportFromId;
    private Integer reportType;//0表示举报内容为帖子，1为评论
    private Integer reportContentType;//举报内容类型
    private String reportUserEmail;//举报人邮箱
    private String reportUserId;//举报人id
    private Boolean reportStatue;//举报处理状态
    private String reportTime;
    private String reportInfo;//举报附加信息
}
