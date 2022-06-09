package com.kylinhunter.plat.web.auth;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-28 01:49
 **/
@Data
@ToString
@NoArgsConstructor
public class LoginForm {
    @ApiModelProperty(value = "用户Code")
    @NotBlank
    private String userCode;
    @ApiModelProperty(value = "密码")
    @NotBlank
    private String password;


}
