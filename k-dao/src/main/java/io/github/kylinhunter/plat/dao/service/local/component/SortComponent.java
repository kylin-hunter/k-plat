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
import io.github.kylinhunter.plat.api.bean.entity.constants.SysCols;
import io.github.kylinhunter.plat.api.bean.sort.Order;
import io.github.kylinhunter.plat.api.bean.sort.ReqSort;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqPage;
import java.util.List;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-11 21:27
 */
public class SortComponent {

  /**
   * @param wrapper wrapper
   * @param reqPage reqPage
   * @return void
   * @title 写sort到wrapper
   * @description
   * @author BiJi'an
   * @date 2021/11/11 3:45 下午
   */
  public <T> void sort(QueryWrapper<T> wrapper, ReqPage reqPage) {
    List<ReqSort> sorts = reqPage.getSorts();
    if (sorts != null && sorts.size() > 0) {
      sorts.forEach(
          sort -> {
            NamePair namePair = NameUtils.toNamePair(sort.getField());
            String column = namePair.getSnake();
            if (Order.ASC.equalTo(sort.getOrder())) {
              wrapper.orderByAsc(column);
            } else {
              wrapper.orderByDesc(column);
            }
          });
    } else {
      wrapper.orderByDesc(SysCols.SYS_UPDATE_TIME);
    }
  }
}
