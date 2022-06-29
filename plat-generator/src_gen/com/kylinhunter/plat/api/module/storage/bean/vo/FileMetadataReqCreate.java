package com.kylinhunter.plat.api.module.storage.bean.vo;

import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * FileMetadataReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FileMetadataReqCreate对象", description = "FileMetadataReqCreate")
public class FileMetadataReqCreate extends ReqCreate implements FileMetadataVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "扩展名")
    private String extension;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "类型 0 ")
    private Integer type;
    @ApiModelProperty(value = "地址")
    private String path;
    @ApiModelProperty(value = "文件名")
    private String name;
    @ApiModelProperty(value = "重复id")
    private String refId;
    @ApiModelProperty(value = "content-type")
    private String contentType;
    @ApiModelProperty(value = "md5")
    private String md5;


}