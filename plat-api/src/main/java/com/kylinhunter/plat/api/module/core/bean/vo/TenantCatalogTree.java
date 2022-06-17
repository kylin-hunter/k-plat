package com.kylinhunter.plat.api.module.core.bean.vo;

import java.util.ArrayList;
import java.util.List;

import com.kylinhunter.plat.api.bean.vo.response.single.DefaultSysResp;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * TenantCatalogResp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantCatalogResp对象", description = "TenantCatalogResp")
public class TenantCatalogTree extends Resp {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "path")
    private Integer level;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "parent_id")
    private String parentId;

    @ApiModelProperty(value = "path")
    private String path;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "状态，预留")
    private Integer status;

    private List<TenantCatalogTree> children;


    public void addChild(TenantCatalogTree child){
        if(children==null){
            children=new ArrayList<>();
        }
        children.add(child);
    }

}