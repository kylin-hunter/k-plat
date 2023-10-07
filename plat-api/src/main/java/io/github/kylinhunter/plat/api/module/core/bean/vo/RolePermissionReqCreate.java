package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * RolePermissionReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RolePermissionReqCreate对象", description = "RolePermissionReqCreate")
public class RolePermissionReqCreate extends ReqCreate implements RolePermissionVO {


    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "permission id")
    private String permissionId;


}