package com.panshare.manager.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @Author Key&Value
 * @Date 2023/2/21 8:40
 * @Version 1.0
 */
@Data
public class QueryCondition {
    @Positive
    private Integer page;
    @Positive
    private Integer pageSize;
    private String condition;
}
