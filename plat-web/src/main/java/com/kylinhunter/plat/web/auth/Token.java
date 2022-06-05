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
public class Token {
    @ApiModelProperty(value = "用户ID")
    @NotBlank
    private String userId;
    @ApiModelProperty(value = "用户姓名")
    @NotBlank
    private String userName;
    @ApiModelProperty(value = "是否是超级管理员")
    @NotNull(message = " true|false")
    private boolean admin;
    @ApiModelProperty(value = "账户类型")
    @NotBlank(message = " for example=> fake or others")
    private String type;

    @ApiModelProperty(value = "过期时间", hidden = true)
    private LocalDateTime expireDate;

    public Token(String userId, String userName, boolean admin, String type) {
        this.userId = userId;
        this.userName = userName;
        this.admin = admin;
        this.type = type;
    }

    public Token(String userId, String userName, boolean admin, String type, LocalDateTime expireDate) {
        this.userId = userId;
        this.userName = userName;
        this.admin = admin;
        this.type = type;
        this.expireDate = expireDate;
    }

}
