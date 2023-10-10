package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.response.single.DefaultSysResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantUserRoleResp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantUserRoleResp对象", description = "TenantUserRoleResp")
public class TenantUserRoleResp extends DefaultSysResp {


    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色 id")
    private String roleId;



}