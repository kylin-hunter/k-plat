package com.kylinhunter.plat.api.bean.filter;

import java.io.Serializable;

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

}
