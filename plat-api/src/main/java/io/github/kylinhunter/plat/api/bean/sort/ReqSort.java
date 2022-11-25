package io.github.kylinhunter.plat.api.bean.sort;

import java.io.Serializable;

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

    public ReqSort(String field, String order) {
        this.field = field;
        this.order = order;
    }

    public ReqSort(String field, Order order) {
        this.field = field;
        this.order = order.code;
    }
}
