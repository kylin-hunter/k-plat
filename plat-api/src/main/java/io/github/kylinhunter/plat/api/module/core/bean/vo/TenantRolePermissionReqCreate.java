package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantRolePermissionReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantRolePermissionReqCreate对象", description = "TenantRolePermissionReqCreate")
public class TenantRolePermissionReqCreate extends ReqCreate implements TenantRolePermissionVO {


    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "permission id")
    private String permissionId;


}