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

    @ApiModelProperty(value = "userCode")
    @NotBlank
    private String userCode;

    @ApiModelProperty(value = "userName")
    @NotBlank
    private String userName;

    @ApiModelProperty(value = "password")
    @NotBlank
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
