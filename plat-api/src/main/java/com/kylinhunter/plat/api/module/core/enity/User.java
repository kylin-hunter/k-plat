package com.kylinhunter.plat.api.module.core.enity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@TableName("kp_user")
@ApiModel(value = "User", description = "")
@NoArgsConstructor
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户来源")
    private String source;

    @ApiModelProperty(value = "用户类型，预留 默认0")
    private Integer type;

    @ApiModelProperty(value = "用户状态，预留 默认0")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String description;
}