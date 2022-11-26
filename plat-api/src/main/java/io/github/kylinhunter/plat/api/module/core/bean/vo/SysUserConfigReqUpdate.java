package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * SysUserConfigReqUpdate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysUserConfigReqUpdate", description = "SysUserConfigReqUpdate")
public class SysUserConfigReqUpdate extends ReqUpdate  implements SysUserConfigVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "type")
    private Integer type;

    @ApiModelProperty(value = "userid")
    private String userId;

    @ApiModelProperty(value = "value")
    private String value;

    @ApiModelProperty(value = "status")
    private Integer status;



}