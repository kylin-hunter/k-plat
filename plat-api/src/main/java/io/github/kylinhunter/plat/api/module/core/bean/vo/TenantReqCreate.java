package io.github.kylinhunter.plat.api.module.core.bean.vo;

import javax.validation.constraints.NotBlank;

import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantReqCreate", description = "TenantReqCreate")
public class TenantReqCreate extends ReqCreate implements TenantVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "code")
    @NotBlank
    private String code;
    @ApiModelProperty(value = "description")
    private String description;
    @ApiModelProperty(value = "type")
    private Integer type;
    @ApiModelProperty(value = "name")
    @NotBlank
    private String name;
    @ApiModelProperty(value = "status")
    private Integer status;


}