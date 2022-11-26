package io.github.kylinhunter.plat.api.module.storage.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author biji'an
 * @since 2022-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kplat_file_metadata")
@ApiModel(value="FileMetadata", description="")
public class FileMetadata extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "type")
    private Integer type;

    @ApiModelProperty(value = "content-type")
    private String contentType;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "extension")
    private String extension;

    @ApiModelProperty(value = "size")
    private Long size;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "md5")
    private String md5;

    @ApiModelProperty(value = "bucket")
    private String bucket;

    @ApiModelProperty(value = "path")
    private String path;


}
