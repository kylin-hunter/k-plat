package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * PermissionReqUpdate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PermissionReqUpdate对象", description = "PermissionReqUpdate")
public class PermissionReqUpdate extends ReqUpdate  implements PermissionVO {


    @ApiModelProperty(value = "权限代码")
    private String code;

    @ApiModelProperty(value = "权限名字")
    private String name;

    @ApiModelProperty(value = "类型 0自建  1 系统自带 ")
    private Integer type;

    @ApiModelProperty(value = "描述")
    private String description;



}