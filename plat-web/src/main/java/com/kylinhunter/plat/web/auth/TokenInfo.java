package com.kylinhunter.plat.web.auth;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.BooleanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kylinhunter.plat.commons.util.DateUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-28 01:49
 **/
@Data
@ToString
@NoArgsConstructor
public class TokenInfo {
    @ApiModelProperty(value = "用户ID")
    @NotBlank
    private String userId;
    @ApiModelProperty(value = "用户姓名")
    @NotBlank
    private String userName;
    @ApiModelProperty(value = "是否是超级管理员")
    @NotNull(message = " true|false")
    private Boolean superuser;
    @ApiModelProperty(value = "账户类型")
    @NotBlank(message = " for example=> fake or others")
    private String accountType;
   
    @ApiModelProperty(value = "过期时间", hidden = true)
    @JsonFormat(pattern = DateUtils.FROMAT_DATE_TIME, timezone = "GMT+8")
    private Date expireDate;

    public TokenInfo(String userId, String userName, boolean superuser, String accountType) {
        this.userId = userId;
        this.userName = userName;
        this.superuser = superuser;
        this.accountType = accountType;
    }

    public TokenInfo(String userId, String userName, boolean superuser, String accountType, Date expireDate) {
        this.userId = userId;
        this.userName = userName;
        this.superuser = superuser;
        this.accountType = accountType;
        this.expireDate = expireDate;
    }

    public boolean isSuperuser() {
        return BooleanUtils.toBoolean(this.getSuperuser());
    }
}
