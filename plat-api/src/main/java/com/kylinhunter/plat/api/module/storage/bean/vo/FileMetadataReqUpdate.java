package com.kylinhunter.plat.api.module.storage.bean.vo;

import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * FileMetadataReqUpdate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FileMetadataReqUpdate对象", description = "FileMetadataReqUpdate")
public class FileMetadataReqUpdate extends ReqUpdate  implements FileMetadataVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "类型 0 ")
    private Integer type;

    @ApiModelProperty(value = "content-type")
    private String contentType;

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "扩展名")
    private String extension;

    @ApiModelProperty(value = "size")
    private Long size;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "md5")
    private String md5;

    @ApiModelProperty(value = "bucket")
    private String bucket;

    @ApiModelProperty(value = "地址")
    private String path;

    @ApiModelProperty(value = "重复id")
    private String refId;

    @ApiModelProperty(value = "重复path")
    private String refPath;



}