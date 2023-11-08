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
package io.github.kylinhunter.plat.dao.service.local.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kylinhunter.commons.name.NamePair;
import io.github.kylinhunter.commons.name.NameUtils;
import io.github.kylinhunter.plat.api.bean.filter.ReqFilter;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqPage;
import io.github.kylinhunter.plat.dao.service.local.ex.FilterCustom;
import io.github.kylinhunter.plat.dao.service.local.ex.FilterCustoms;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-11 21:27
 */
@RequiredArgsConstructor
public class FilterComponent {

  private final FilterCustoms filterCustoms;

  /**
   * @param wrapper wrapper
   * @param reqPage reqPage
   * @return void
   * @title 写filter到wrapper
   * @description
   */
  public <T> void filter(QueryWrapper<T> wrapper, ReqPage reqPage) {
    List<ReqFilter> filters = reqPage.getFilters();
    if (filters != null && filters.size() > 0) {
      filters.forEach(
          filter -> {
            NamePair namePair = NameUtils.toNamePair(filter.getField());
            String field = namePair.getCamel();
            String column = namePair.getSnake();
            FilterCustom filterCustom = filterCustoms.getFilterCustom(field);
            if (filterCustom != null) { // 特殊的 filter 处理
              filterCustom.filter(wrapper, filter);
            } else {
              List<String> values = Arrays.asList(filter.getValue().split(","));
              wrapper.in(column, values);
            }
          });
    }
  }
}
