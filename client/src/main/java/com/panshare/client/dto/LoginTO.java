package com.panshare.client.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
public class LoginTO {
    @Email
    private String email;
    @NotBlank(message = "密码不能为空")
    private String password;
}
