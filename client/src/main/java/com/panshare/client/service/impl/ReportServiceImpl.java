package com.panshare.client.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panshare.client.common.Cache;
import com.panshare.client.common.R;
import com.panshare.client.dto.ReportDTO;
import com.panshare.client.mapper.ReportMapper;
import com.panshare.client.pojo.Report;
import com.panshare.client.service.ReportService;
import com.panshare.client.utils.RedisUtils;
import com.panshare.client.utils.ThreadLocalUtil;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public R saveReport(ReportDTO reportDTO) {
        Integer userId = ThreadLocalUtil.get();
        String key = Cache.REPORT + userId + ":" + reportDTO.getReportType() + ":" + reportDTO.getReportFromId();
        if (this.redisUtils.hasKey(key)) {
            return R.error().message("你已经举报过该帖子，请不要重复举报！");
        }
        this.redisUtils.set(key, reportDTO, 15L, TimeUnit.DAYS);
        Report report = new Report();
        report.setReportUserId(userId);
        BeanUtil.copyProperties(reportDTO, report, new String[0]);
        boolean save = this.reportMapper.saveReport(report);
        return R.ok().message(String.valueOf(save));
    }
}
