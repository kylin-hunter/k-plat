package io.github.kylinhunter.plat.api.module.core.bean.entity;

import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author biji'an
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kplat_tenant_user")
@ApiModel(value="TenantUser")
public class TenantUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "tenantId")
    private String tenantId;

    @ApiModelProperty(value = "userId")
    private String userId;

    @ApiModelProperty(value = "type")
    private Integer type;

    @ApiModelProperty(value = "status")
    private Integer status;

    @ApiModelProperty(value = "description")
    private String description;


}
