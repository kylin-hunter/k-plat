package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * UserRoleReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserRoleReqCreate对象", description = "UserRoleReqCreate")
public class UserRoleReqCreate extends ReqCreate implements UserRoleVO {


    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "角色 id")
    private String roleId;


}