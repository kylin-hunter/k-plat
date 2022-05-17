package com.kylinhunter.plat.api.bean.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ReqFilters", description = "ReqFilters")
public class ReqFilters implements Serializable {

    @ApiModelProperty(value = "filters")
    private List<ReqFilter> filters;

    @ApiModelProperty(value = "init", hidden = true)
    private boolean init = false;

    @ApiModelProperty(value = "cacheDatas", hidden = true)
    private FilterDatas cacheDatas;

    public ReqFilters(List<ReqFilter> filters) {
        this.filters = filters;
    }

    public void add(ReqFilter filter) {
        if (filters == null) {
            filters = new ArrayList<>();
        }
        filters.add(filter);

    }

    public void forEach(Consumer<ReqFilter> action) {
        if (filters != null) {
            filters.forEach(action);
        }
    }

    public ReqFilter get(String fieldOrColumn) {
        if (cacheDatas != null) {
            return cacheDatas.get(fieldOrColumn);
        } else {
            if (filters != null && filters.size() > 0) {
                cacheDatas = new FilterDatas(filters);
                return cacheDatas.get(fieldOrColumn);
            } else {
                return null;
            }
        }
    }

    public int size() {
        if (filters != null) {
            return filters.size();
        }
        return 0;
    }
}
