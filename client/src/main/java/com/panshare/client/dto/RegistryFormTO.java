package com.panshare.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegistryFormTO {
    @NotBlank(message = "昵称不能为空")
    @JsonProperty("userName")
    @Size(min = 3, max = 8, message = "昵称字数请限制在3~8个字符之间")
    private String userName;
    @NotBlank(message = "邮箱不能为空")
    @JsonProperty("email")
    @Email
    private String email;
    @Length(min = 6, message = "密码长度必须大于6")
    @JsonProperty("password")
    private String password;
    @NotBlank(message = "验证码不能为空")
    @JsonProperty("code")
    private String code;

    @JsonProperty("userName")
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    @JsonProperty("email")
    public void setEmail(final String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public void setPassword(final String password) {
        this.password = password;
    }

    @JsonProperty("code")
    public void setCode(final String code) {
        this.code = code;
    }
}
