package com.kylinhunter.plat.kb.api.module.core.bean.entity;

import java.math.BigDecimal;
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
 * @since 2022-07-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kb_doc")
@ApiModel(value="Doc对象", description="")
public class Doc extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "cat_id")
    private String catId;

    @ApiModelProperty(value = "secondary_cat_id")
    private String secondaryCatId;

    @ApiModelProperty(value = "类型 0 普通文本  1 富文本 ")
    private Integer type;

    @ApiModelProperty(value = "title")
    private String title;

    @ApiModelProperty(value = "content")
    private String content;

    @ApiModelProperty(value = "summary")
    private String summary;

    @ApiModelProperty(value = "keywords")
    private String keywords;

    @ApiModelProperty(value = "tags")
    private String tags;

    @ApiModelProperty(value = "md5")
    private String md5;

    @ApiModelProperty(value = "版本号")
    private BigDecimal version;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "描述")
    private String description;


}
