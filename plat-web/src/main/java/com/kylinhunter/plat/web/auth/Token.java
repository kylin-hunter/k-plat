package com.kylinhunter.plat.web.auth;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Token {
    @ApiModelProperty(value = "用户Code")
    @NotBlank
    private String userCode;

    @ApiModelProperty(value = "用户姓名")
    @NotBlank
    private String userName;

    @ApiModelProperty(value = "是否是超级管理员")
    @NotNull(message = " true|false")
    private boolean admin;

    @ApiModelProperty(value = "账户类型")
    @NotBlank(message = "")
    private int type;

    @ApiModelProperty(value = "过期时间", hidden = true)
    private LocalDateTime expireDate;

}
