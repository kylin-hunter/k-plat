package com.kylinhunter.plat.api.module.core.bean.vo;

import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantUserConfigReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantUserConfigReqCreate对象", description = "TenantUserConfigReqCreate")
public class TenantUserConfigReqCreate extends ReqCreate implements TenantUserConfigVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "配置编码")
    private String code;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "类型 0 字符串")
    private Integer type;
    @ApiModelProperty(value = "userid")
    private String userId;
    @ApiModelProperty(value = "配置项的值")
    private String value;
    @ApiModelProperty(value = "状态")
    private Integer status;


}