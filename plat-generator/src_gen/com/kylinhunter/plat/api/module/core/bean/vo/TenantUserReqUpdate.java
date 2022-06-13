package com.kylinhunter.plat.api.module.core.bean.vo;

import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantUserReqUpdate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantUserReqUpdate对象", description = "TenantUserReqUpdate")
public class TenantUserReqUpdate extends ReqUpdate  implements TenantUserVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "userId")
    private String userid;

    @ApiModelProperty(value = "tenantId")
    private String tenantid;

    @ApiModelProperty(value = "状态，预留")
    private Integer status;



}