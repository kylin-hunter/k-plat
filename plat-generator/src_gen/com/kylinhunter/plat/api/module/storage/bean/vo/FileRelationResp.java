package com.kylinhunter.plat.api.module.storage.bean.vo;

import com.kylinhunter.plat.api.bean.vo.response.single.DefaultSysResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * FileRelationResp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FileRelationResp对象", description = "FileRelationResp")
public class FileRelationResp extends DefaultSysResp {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "类型 0 ")
    private Integer type;

    @ApiModelProperty(value = "知识ID")
    private String masterId;

    @ApiModelProperty(value = "文件ID")
    private String fileId;



}