package com.kylinhunter.plat.api.bean.vo.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 **/
@Data
@ApiModel(value = "ReqTenant", description = "ReqTenant")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReqTenant implements Serializable {
    private static final long serialVersionUID = -8450405452557433712L;
    @ApiModelProperty(value = "tenantId")
    protected String tenantId = "";

}
