package com.panshare.manager.controller;


import com.panshare.manager.common.R;
import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @PostMapping("/")
    public R listReport(@RequestBody @Valid QueryCondition queryCondition){
        return reportService.queryReportByCondition(queryCondition);
    }
    @PostMapping("/handler")
    public R handler(@RequestParam("reportId") @NotNull Integer reportId){
        boolean res=reportService.handler(reportId);
        return R.ok().data("flag",res);
    }
}

