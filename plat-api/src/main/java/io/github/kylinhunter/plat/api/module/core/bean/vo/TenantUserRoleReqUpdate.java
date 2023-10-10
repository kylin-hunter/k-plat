package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantUserRoleReqUpdate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantUserRoleReqUpdate对象", description = "TenantUserRoleReqUpdate")
public class TenantUserRoleReqUpdate extends ReqUpdate  implements TenantUserRoleVO {


    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色 id")
    private String roleId;



}