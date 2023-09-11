/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.api.bean.vo.query;

import com.google.common.collect.Lists;
import io.github.kylinhunter.plat.api.bean.filter.ReqFilter;
import io.github.kylinhunter.plat.api.bean.sort.Order;
import io.github.kylinhunter.plat.api.bean.sort.ReqSort;
import io.github.kylinhunter.plat.api.constants.SwaggerConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 */
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ApiModel(value = "ReqPage", description = "ReqPage")
@NoArgsConstructor
@AllArgsConstructor
public class ReqPage extends ReqQuery implements Serializable {
  private static final long serialVersionUID = -8450405452557433712L;

  @ApiModelProperty(
      value = "page enabled  ,If it is turned off, it may cause performance problems",
      hidden = true)
  private boolean pageable = true;

  @ApiModelProperty(value = "page no", example = "1")
  protected int pn = 1;

  @ApiModelProperty(value = "page size", example = "10")
  protected int ps = 10;

  @ApiModelProperty(value = "keyword")
  private String keyword;

  @ApiModelProperty(value = "start time", example = SwaggerConst.SAMPLE_DATE_TIME)
  private LocalDateTime startTime;

  @ApiModelProperty(value = "end time", example = SwaggerConst.SAMPLE_DATE_TIME)
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
