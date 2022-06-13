package com.kylinhunter.plat.api.module.core.bean.entity;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-06-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kplat_tenant_user")
@ApiModel(value="TenantUser对象", description="")
public class TenantUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "tenantId")
    @TableField("tenantId")
    private String tenantid;

    @ApiModelProperty(value = "userId")
    @TableField("userId")
    private String userid;

    @ApiModelProperty(value = "状态，预留")
    private Integer status;

    @ApiModelProperty(value = "描述")
    private String description;


}
