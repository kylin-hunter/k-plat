package com.kylinhunter.plat.api.bean.vo;

import java.io.Serializable;

import com.kylinhunter.plat.api.bean.vo.constants.VoType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 **/
@Data
@ApiModel(value = "BasicVO", description = "BasicVO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class BasicVO implements Serializable {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "voType", hidden = true)
    private final VoType voType;

    @ApiModelProperty(value = "tenantId")
    protected String tenantId = "";

}
