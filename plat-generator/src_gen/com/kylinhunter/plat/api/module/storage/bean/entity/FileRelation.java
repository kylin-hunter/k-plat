package com.kylinhunter.plat.api.module.storage.bean.entity;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kplat_file_relation")
@ApiModel(value="FileRelation对象", description="")
public class FileRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型 0 ")
    private Integer type;

    @ApiModelProperty(value = "知识ID")
    private String masterId;

    @ApiModelProperty(value = "文件ID")
    private String fileId;


}
