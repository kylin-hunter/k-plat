package com.kylinhunter.plat.api.module.core.bean.vo;

import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantUserReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantUserReqCreate对象", description = "TenantUserReqCreate")
public class TenantUserReqCreate extends ReqCreate implements TenantUserVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "userId")
    private String userId;
    @ApiModelProperty(value = "tenantId")
    private String tenantId;
    @ApiModelProperty(value = "用户类型，预留 默认0")
    private Integer type;
    @ApiModelProperty(value = "状态，预留")
    private Integer status;


}