package com.kylinhunter.plat.api.bean.sort;

import java.io.Serializable;

import com.kylinhunter.plat.commons.util.name.NamePair;
import com.kylinhunter.plat.commons.util.name.NamePairUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-11 01:01
 **/
@Data
@ApiModel(value = "ReqSort", description = "ReqSort")
public class ReqSort implements Serializable {
    @ApiModelProperty(value = "field)")
    private String field;

    @ApiModelProperty(value = "asc|desc")
    private String order;

    @ApiModelProperty(value = "namePair", hidden = true)
    private NamePair namePair;

    public ReqSort(String field, String order) {
        this.field = field;
        this.order = order;
        this.namePair = NamePairUtils.toNamePair(field);
    }

    public ReqSort(String field, Order order) {
        this.field = field;
        this.order = order.code;
        this.namePair = NamePairUtils.toNamePair(field);
    }

    public String getColumn() {
        if (namePair != null) {
            return namePair.getSnake();
        }
        return "";
    }
}
