package com.kylinhunter.plat.api.bean.filter;

import java.io.Serializable;

import com.kylinhunter.plat.commons.util.name.NamePair;
import com.kylinhunter.plat.commons.util.name.NamePairUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-11 01:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ReqFilter", description = "ReqFilter")
public class ReqFilter implements Serializable {
    @ApiModelProperty(value = "field")
    private String field;

    @ApiModelProperty(value = "value")
    private String value;

    @ApiModelProperty(value = "namePair", hidden = true)
    private NamePair namePair;

    public ReqFilter(String field, String value) {
        this.field = field;
        this.value = value;
        this.namePair = NamePairUtils.toNamePair(field);

    }

    public String getColumn() {
        if (namePair != null) {
            return namePair.getUnderline();
        }
        return "";
    }

}
