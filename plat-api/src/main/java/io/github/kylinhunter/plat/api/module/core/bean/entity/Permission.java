package io.github.kylinhunter.plat.api.module.core.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
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
 * @since 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kplat_permission")
@ApiModel(value="Permission对象", description="")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限代码")
    private String code;

    @ApiModelProperty(value = "权限名字")
    private String name;

    @ApiModelProperty(value = "类型 0自建  1 系统自带 ")
    private Integer type;

    @ApiModelProperty(value = "描述")
    private String description;


}
