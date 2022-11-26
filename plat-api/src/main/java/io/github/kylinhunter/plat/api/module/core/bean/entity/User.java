package io.github.kylinhunter.plat.api.module.core.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("kplat_user")
@ApiModel(value = "User")
@NoArgsConstructor
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "userCode")
    private String userCode;

    @ApiModelProperty(value = "userName")
    private String userName;


    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "source")
    private String source;

    @ApiModelProperty(value = "type")
    private Integer type;

    @ApiModelProperty(value = "status")
    private Integer status;

    @ApiModelProperty(value = "description")
    private String description;
}