package io.github.kylinhunter.plat.api.module.storage.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;

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
 * @since 2022-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FileMetadataReqUpdate", description = "FileMetadataReqUpdate")
public class FileMetadataReqUpdate extends ReqUpdate  implements FileMetadataVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "extension")
    private String extension;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "type")
    private Integer type;

    @ApiModelProperty(value = "bucket")
    private String bucket;

    @ApiModelProperty(value = "path")
    private String path;

    @ApiModelProperty(value = "size")
    private Long size;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "content-type")
    private String contentType;

    @ApiModelProperty(value = "md5")
    private String md5;



}