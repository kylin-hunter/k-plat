package com.kylinhunter.plat.api.module.kb.bean.vo;

import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * DocReqUpdate 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DocReqUpdate对象", description = "DocReqUpdate")
public class DocReqUpdate extends ReqUpdate  implements DocVO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "summary")
    private String summary;

    @ApiModelProperty(value = "keywords")
    private String keywords;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "title")
    private String title;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "版本号")
    private BigDecimal version;

    @ApiModelProperty(value = "content")
    private String content;

    @ApiModelProperty(value = "tags")
    private String tags;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "md5")
    private String md5;



}