package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
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
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantUserReqUpdate", description = "TenantUserReqUpdate")
public class TenantUserReqUpdate extends ReqUpdate  implements TenantUserVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "userId")
    private String userId;

    @ApiModelProperty(value = "tenantId")
    private String tenantId;

    @ApiModelProperty(value = "type")
    private Integer type;

    @ApiModelProperty(value = "status")
    private Integer status;



}