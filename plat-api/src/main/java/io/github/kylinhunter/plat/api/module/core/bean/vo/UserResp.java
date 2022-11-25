package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.response.single.DefaultSysResp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * UserResp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ApiModel(value = "UserResp", description = "")
public class UserResp extends DefaultSysResp {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userCode;

    @ApiModelProperty(value = "用户名")
    private String userName;

    //    @ApiModelProperty(value = "密码")
    //    private String password;

    @ApiModelProperty(value = "用户来源")
    private String source;

    @ApiModelProperty(value = "用户状态，预留 默认0")
    private Integer type;

    @ApiModelProperty(value = "用户状态，预留 默认0")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String description;

}