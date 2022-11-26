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
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kplat_tenant_config")
@ApiModel(value="TenantConfig")
public class TenantConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "type 0 string")
    private Integer type;

    @ApiModelProperty(value = "status")
    private Integer status;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "value")
    private String value;


}
