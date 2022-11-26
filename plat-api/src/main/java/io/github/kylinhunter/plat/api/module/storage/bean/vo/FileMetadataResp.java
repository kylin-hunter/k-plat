package io.github.kylinhunter.plat.api.module.storage.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.response.single.DefaultSysResp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * FileMetadataResp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FileMetadataResp对象", description = "FileMetadataResp")
public class FileMetadataResp extends DefaultSysResp {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "扩展名")
    private String extension;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "类型 0 ")
    private Integer type;

    @ApiModelProperty(value = "bucket")
    private String bucket;

    @ApiModelProperty(value = "地址")
    private String path;

    @ApiModelProperty(value = "size")
    private Long size;

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "content-type")
    private String contentType;

    @ApiModelProperty(value = "md5")
    private String md5;



}