package io.github.kylinhunter.plat.api.module.core.bean.vo;

import javax.validation.constraints.NotBlank;

import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * RoleReqUpdate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RoleReqUpdate对象", description = "RoleReqUpdate")
public class RoleReqUpdate extends ReqUpdate  implements RoleVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "code")
    @NotBlank
    private String code;

    @ApiModelProperty(value = "name")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "角色状态，预留")
    private Integer status;


    @ApiModelProperty(value = "描述")
    private String description;








}