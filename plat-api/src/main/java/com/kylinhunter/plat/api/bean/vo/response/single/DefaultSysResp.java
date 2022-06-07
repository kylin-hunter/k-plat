package com.kylinhunter.plat.api.bean.vo.response.single;

import java.time.LocalDateTime;

import com.kylinhunter.plat.api.constants.SwaggerConst;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * DefaultSysResp
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@ApiModel(value = "DefaultSysResp", description = "DefaultSysResp")
public class DefaultSysResp extends Resp {

    @ApiModelProperty(value = "sysTenantId", hidden = true)
    private String sysTenantId;

    @ApiModelProperty(value = "sysCreatedUserId", hidden = true)
    private String sysCreatedUserId;

    @ApiModelProperty(value = "sysCreatedUserName", hidden = true)
    private String sysCreatedUserName;

    @ApiModelProperty(value = "sysCreatedTime", hidden = true, example = SwaggerConst.SAMPLE_DATE_TIME)
    private LocalDateTime sysCreatedTime;

    @ApiModelProperty(value = "sysUpdateUserId", hidden = true)
    private String sysUpdateUserId;

    @ApiModelProperty(value = "sysUpdateUserName", hidden = true)
    private String sysUpdateUserName;

    @ApiModelProperty(value = "sysUpdateTime", hidden = true, example = SwaggerConst.SAMPLE_DATE_TIME)
    private LocalDateTime sysUpdateTime;

}