package com.kylinhunter.plat.api.module.core.bean.entity;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
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
 * @since 2022-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kplat_tenant_catalog")
@ApiModel(value="TenantCatalog对象", description="")
public class TenantCatalog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "状态，预留")
    private Integer status;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "path")
    private String path;

    @ApiModelProperty(value = "path")
    private Integer level;

    @ApiModelProperty(value = "parent_id")
    private String parentId;


}
