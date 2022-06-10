package com.kylinhunter.plat.web.auth;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:49
 **/
@Data
@ToString
@NoArgsConstructor
public class LoginForm {
    @ApiModelProperty(value = "userId")
    @NotBlank
    private String userCode;
    @ApiModelProperty(value = "password")
    @NotBlank
    private String password;

}
