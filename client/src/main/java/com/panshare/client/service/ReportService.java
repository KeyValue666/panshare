package com.panshare.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panshare.client.common.R;
import com.panshare.client.dto.ReportDTO;
import com.panshare.client.pojo.Report;

/* loaded from: ReportService.class */
public interface ReportService extends IService<Report> {
    R saveReport(ReportDTO reportDTO);
}
