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
package io.github.kylinhunter.plat.dao.service.local.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.kylinhunter.commons.exception.embed.biz.DBException;
import io.github.kylinhunter.commons.utils.bean.BeanCopyUtils;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.bean.entity.constants.SysCols;
import io.github.kylinhunter.plat.api.bean.vo.VO;
import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqPage;
import io.github.kylinhunter.plat.api.bean.vo.response.single.Resp;
import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.github.kylinhunter.plat.api.page.PageData;
import io.github.kylinhunter.plat.dao.service.local.component.FilterComponent;
import io.github.kylinhunter.plat.dao.service.local.component.SortComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 22:59
 */
@Component
@Primary
public class QueryComplexInterceptor<
        T extends BaseEntity,
        C extends ReqCreate,
        U extends ReqUpdate,
        Z extends Resp,
        V extends VO,
        Q extends ReqPage>
    extends BasicInterceptor<T, C, U, Z, V, Q> {

  @Autowired private SortComponent sortComponent;
  @Autowired private FilterComponent filterComponent;

  public QueryWrapper<T> before(Q q, boolean tenantSupported) {
    QueryWrapper<T> query = Wrappers.query();

    if (tenantSupported) {
      final String tenantId = this.checkAndGetTenantId();
      query.eq(SysCols.SYS_TENANT_ID, tenantId);
    }

    if (!q.isWithLogicDelData()) {
      query.eq(SysCols.SYS_DELETE_FLAG, "0");
    }
    sortComponent.sort(query, q);
    filterComponent.filter(query, q);
    return query;
  }

  public PageData<Z> after(Q reqQueryPage, Page<T> page, Class<Z> respClass) {
    PageData<Z> pageData = new PageData<>();
    pageData.setPn(page.getCurrent());
    pageData.setPs(page.getSize());
    pageData.setPages(page.getPages());
    pageData.setTotal(page.getTotal());
    for (T r : page.getRecords()) {
      try {
        Z response = respClass.newInstance();
        BeanCopyUtils.copyProperties(r, response);
        pageData.getBody().add(response);
      } catch (Exception e) {
        throw new DBException("copy bean error", e);
      }
    }
    return pageData;
  }
}
