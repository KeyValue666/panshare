package com.panshare.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panshare.manager.common.R;
import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.pojo.Report;
import com.panshare.manager.mapper.ReportMapper;
import com.panshare.manager.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panshare.manager.vo.ReportVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@Service
@Slf4j
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public R queryReportByCondition(QueryCondition queryCondition) {
        PageHelper.startPage(queryCondition.getPage(), queryCondition.getPageSize());
        List<ReportVO> list = reportMapper.queryReport(queryCondition);
        PageInfo<ReportVO> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("report", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return R.ok().data(map);
    }

    @Override
    public boolean handler(Integer reportId) {
        QueryWrapper<Report> wrapper = new QueryWrapper<>();
        wrapper.eq("report_id", reportId);
        Report report = new Report();
        report.setReportIsFinish(true);
        int update = reportMapper.update(report, wrapper);
        return update > 0;
    }
}
