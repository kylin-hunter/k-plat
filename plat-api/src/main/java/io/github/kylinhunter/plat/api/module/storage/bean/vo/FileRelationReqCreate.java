package io.github.kylinhunter.plat.api.module.storage.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * FileRelationReqCreate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FileRelationReqCreate对象", description = "FileRelationReqCreate")
public class FileRelationReqCreate extends ReqCreate implements FileRelationVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "type")
    private Integer type;
    @ApiModelProperty(value = "masterId")
    private String masterId;
    @ApiModelProperty(value = "fileId")
    private String fileId;


}