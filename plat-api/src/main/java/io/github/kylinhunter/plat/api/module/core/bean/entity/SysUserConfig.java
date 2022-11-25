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
@TableName("kplat_sys_user_config")
@ApiModel(value="SysUserConfig对象", description="")
public class SysUserConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型 0 字符串")
    private Integer type;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "userid")
    private String userId;

    @ApiModelProperty(value = "配置编码")
    private String code;

    @ApiModelProperty(value = "配置项的值")
    private String value;


}
