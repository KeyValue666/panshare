package com.panshare.client.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReportDTO {
    @NotNull
    private Integer reportType;
    @NotNull
    private Integer reportFromId;
    @NotNull
    private Integer reportContentType;
    private String reportInfo;
}
