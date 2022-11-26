package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantCatalogReqUpdate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantCatalogReqUpdate", description = "TenantCatalogReqUpdate")
public class TenantCatalogReqUpdate extends ReqUpdate  implements TenantCatalogVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "path")
    private Integer level;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "type")
    private Integer type;

    @ApiModelProperty(value = "parent_id")
    private String parentId;

    @ApiModelProperty(value = "path")
    private String path;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "status")
    private Integer status;



}