package io.github.kylinhunter.plat.api.module.core.bean.vo;

import javax.validation.constraints.NotBlank;

import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * UserReqUpdate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ApiModel(value = "UserReqUpdate", description = "UserReqUpdate")
public class UserReqUpdate extends ReqUpdate implements UserVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @NotBlank
    private String userCode;

    @ApiModelProperty(value = "用户名")
    @NotBlank
    private String userName;

    @ApiModelProperty(value = "密码")
    @NotBlank
    private String password;

    @ApiModelProperty(value = "用户来源")
    private String source;

    @ApiModelProperty(value = "用户类型，预留 默认0")
    private Integer type;

    @ApiModelProperty(value = "用户状态，预留 默认0")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String description;
}
