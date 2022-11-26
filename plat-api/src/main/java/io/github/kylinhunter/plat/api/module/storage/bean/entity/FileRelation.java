package io.github.kylinhunter.plat.api.module.storage.bean.entity;

import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
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

    @ApiModelProperty(value = "type")
    private Integer type;

    @ApiModelProperty(value = "masterId")
    private String masterId;

    @ApiModelProperty(value = "fileId")
    private String fileId;


}
