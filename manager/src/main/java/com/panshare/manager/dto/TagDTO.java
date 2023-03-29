package com.panshare.manager.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author Key&Value
 * @Date 2023/2/21 9:58
 * @Version 1.0
 */
@Data
public class TagDTO {
    @NotBlank
    private String tagColor;
    @NotBlank
    private String tagIcon;
    @NotBlank
    private String tagName;
    @NotNull
    private Boolean tagIsPublic;
}
