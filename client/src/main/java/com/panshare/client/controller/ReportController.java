package com.panshare.client.controller;

import com.panshare.client.common.R;
import com.panshare.client.dto.ReportDTO;
import com.panshare.client.service.ReportService;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping({"/report"})
@RestController
@Validated
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping({"/"})
    public R uploadReport(@Valid @RequestBody ReportDTO reportDTO) {
        return this.reportService.saveReport(reportDTO);
    }
}
