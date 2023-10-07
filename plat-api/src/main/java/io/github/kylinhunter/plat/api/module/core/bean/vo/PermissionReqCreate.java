package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * PermissionReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PermissionReqCreate对象", description = "PermissionReqCreate")
public class PermissionReqCreate extends ReqCreate implements PermissionVO {


    @ApiModelProperty(value = "权限代码")
    private String code;
    @ApiModelProperty(value = "权限名字")
    private String name;
    @ApiModelProperty(value = "类型 0自建  1 系统自带 ")
    private Integer type;
    @ApiModelProperty(value = "描述")
    private String description;


}