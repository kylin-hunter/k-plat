package com.kylinhunter.plat.api.bean.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-11 01:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ReqFilters", description = "ReqFilters")
class FilterDatas implements Serializable {

    @ApiModelProperty(value = "columnFilters", hidden = true)
    private Map<String, ReqFilter> columnFilters = new HashMap<>();

    @ApiModelProperty(value = "fieldFilters", hidden = true)
    private Map<String, ReqFilter> fieldFilters = new HashMap<>();

    public FilterDatas(List<ReqFilter> filters) {
        filters.forEach(this::add);
    }

    public void add(ReqFilter filter) {
        String column = filter.getColumn();
        if (column != null && column.length() > 0) {
            columnFilters.put(column, filter);
        }
        String field = filter.getField();
        if (field != null && field.length() > 0) {
            fieldFilters.put(field, filter);
        }
    }

    public ReqFilter get(String fieldOrColumn) {
        ReqFilter reqFilter = columnFilters.get(fieldOrColumn);
        if (reqFilter != null) {
            return reqFilter;
        } else {
            return fieldFilters.get(fieldOrColumn);
        }
    }

}
