package com.kylinhunter.plat.api.auth;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Token {
    @ApiModelProperty(value = "tenantId", hidden = true)
    private String tenantId = "";

    @ApiModelProperty(value = "userId", hidden = true)
    private String userId = "";

    @ApiModelProperty(value = "userCode", hidden = true)
    private String userCode = "";

    @ApiModelProperty(value = "userName", hidden = true)
    private String userName = "";

    @ApiModelProperty(value = "账户类型")
    private int userType;

    @ApiModelProperty(value = "过期时间", hidden = true)
    private LocalDateTime expireDate;

}
