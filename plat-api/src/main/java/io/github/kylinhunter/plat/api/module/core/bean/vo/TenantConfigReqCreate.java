package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantConfigReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantConfigReqCreate", description = "TenantConfigReqCreate")
public class TenantConfigReqCreate extends ReqCreate implements TenantConfigVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "code")
    private String code;
    @ApiModelProperty(value = "description")
    private String description;
    @ApiModelProperty(value = "type")
    private Integer type;
    @ApiModelProperty(value = "value")
    private String value;
    @ApiModelProperty(value = "status")
    private Integer status;


}