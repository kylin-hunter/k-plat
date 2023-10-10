package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantRolePermissionReqUpdate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantRolePermissionReqUpdate对象", description = "TenantRolePermissionReqUpdate")
public class TenantRolePermissionReqUpdate extends ReqUpdate  implements TenantRolePermissionVO {


    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "permission id")
    private String permissionId;



}