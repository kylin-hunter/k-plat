package com.kylinhunter.plat.api.module.core.bean.vo;

import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * RoleReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RoleReqCreate对象", description = "RoleReqCreate")
public class RoleReqCreate extends ReqCreate {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "角色类型")
    private Integer type;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "角色状态，预留")
    private Integer status;


}