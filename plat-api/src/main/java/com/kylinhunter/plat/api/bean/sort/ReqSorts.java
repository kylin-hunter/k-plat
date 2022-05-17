package com.kylinhunter.plat.api.bean.sort;

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
@ApiModel(value = "ReqSorts", description = "ReqSorts")
public class ReqSorts implements Serializable {
    private List<ReqSort> sorts;
    @ApiModelProperty(value = "init", hidden = true)
    private boolean init = false;

    public ReqSorts(List<ReqSort> sorts) {
        this.sorts = sorts;
    }

    public void add(ReqSort sort) {
        if (sorts == null) {
            sorts = new ArrayList<>();
        }
        sorts.add(sort);
    }

    public void forEach(Consumer<ReqSort> action) {
        if (sorts != null) {
            sorts.forEach(action);
        }
    }

    public int size() {
        if (sorts != null) {
            return sorts.size();
        }
        return 0;
    }
}
