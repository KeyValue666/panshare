package com.panshare.manager.service;

import com.panshare.manager.common.R;
import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.pojo.Report;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
public interface ReportService extends IService<Report> {

    /**
     * @param queryCondition
     * @return
     */
    R queryReportByCondition(QueryCondition queryCondition);

    /**
     * 标记处理完的举报
     * @param reportId
     * @return
     */
    boolean handler(Integer reportId);
}
