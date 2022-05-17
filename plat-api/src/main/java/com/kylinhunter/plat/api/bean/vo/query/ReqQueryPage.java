package com.kylinhunter.plat.api.bean.vo.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.google.common.collect.Lists;
import com.kylinhunter.plat.api.bean.filter.ReqFilter;
import com.kylinhunter.plat.api.bean.sort.Order;
import com.kylinhunter.plat.api.bean.sort.ReqSort;
import com.kylinhunter.plat.api.constants.SwaggerConst;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-05 21:42
 **/
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ApiModel(value = "ReqQueryPage 对象", description = "ReqQueryPage")
@NoArgsConstructor
@AllArgsConstructor
public class ReqQueryPage extends ReqQuery implements Serializable {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "开启分页，（注意，关闭分页可能带来严重性能问题！！）", hidden = true)
    private boolean pageable = true;
    @ApiModelProperty(value = "当前页", example = "1")
    protected int pn = 1;
    @ApiModelProperty(value = "页大小", example = "10")
    protected int ps = 10;

    @ApiModelProperty(value = "搜索关键字")
    private String keyword;

    @ApiModelProperty(value = "开始时间", example = SwaggerConst.SAMPLE_DATE_TIME)
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间", example = SwaggerConst.SAMPLE_DATE_TIME)
    private LocalDateTime endTime;

    @ApiModelProperty(value = "sorts")
    private List<ReqSort> sorts;

    @ApiModelProperty(value = "filters")
    private List<ReqFilter> filters;

    /**
     * @param field field
     * @param order order
     * @return void
     * @title addOrder
     * @description
     * @author BiJi'an
     * @date 2022-05-12 02:51
     */
    public void addOrder(String field, Order order) {
        if (sorts == null) {
            sorts = Lists.newArrayList();
        }
        sorts.add(new ReqSort(field, order));
    }

    public void addFilter(String field, String value) {
        if (filters == null) {
            filters = Lists.newArrayList();
        }
        filters.add(new ReqFilter(field, value));
    }

}
