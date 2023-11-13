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
package io.github.kylinhunter.plat.core.service.local.component;

import io.github.kylinhunter.commons.exception.embed.InitException;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.constants.init.DefaultTenantCatalogs;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.core.dao.mapper.TenantCatalogMapper;
import io.github.kylinhunter.plat.dao.service.local.interceptor.BasicInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-18 14:56
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TenantCatalogComponent {

  private final TenantCatalogMapper tenantCatalogMapper;

  public TenantCatalog save(int type, String code, String name, int sort, String parentCode) {
    String tenantId = TraceHolder.get().getUserContext().getTenantId();

    TenantCatalog parent = tenantCatalogMapper.findByCode(tenantId, type, parentCode);
    if (parent == null) {
      throw new InitException("parent is null: " + parentCode);
    }

    TenantCatalog catalog = tenantCatalogMapper.findByCode(tenantId, type, code);

    if (catalog != null) {
      catalog.setName(name);
      catalog.setSort(sort);
      this.setLevelAndPathFromParent(catalog, parent);
      BasicInterceptor.setUpdateMsg(catalog);
      tenantCatalogMapper.updateById(catalog);
      log.info("update catalog={}", catalog);

    } else {
      catalog = new TenantCatalog();
      catalog.setType(type);
      catalog.setCode(code);
      catalog.setName(name);
      catalog.setSort(sort);
      this.setLevelAndPathFromParent(catalog, parent);
      BasicInterceptor.setCreateMsg(catalog);
      tenantCatalogMapper.insert(catalog);
      log.info("create catalog={}", code);
    }
    return catalog;
  }

  /**
   * @param curCatalog curCatalog
   * @param parent parent
   * @return void
   * @title setLevelAndPathFromParent
   * @description setLevelAndPathFromParent
   * @author BiJi'an
   * @date 2023-10-24 15:05
   */
  public void setLevelAndPathFromParent(TenantCatalog curCatalog, TenantCatalog parent) {
    curCatalog.setParentId(parent.getId());
    curCatalog.setLevel(parent.getLevel() + 1);
    curCatalog.setPath(parent.getPath() + "_" + parent.getId());
  }

  /**
   * @param type type
   * @return io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog
   * @title initRoot
   * @description initRoot
   * @author BiJi'an
   * @date 2023-10-24 15:09
   */
  public TenantCatalog initRoot(int type) {
    String tenantId = TraceHolder.get().getUserContext().getTenantId();

    TenantCatalog tenantCatalog =
        tenantCatalogMapper.findByCode(tenantId, type, DefaultTenantCatalogs.ROOT_CODE);
    if (tenantCatalog == null) {
      tenantCatalog = new TenantCatalog();
      tenantCatalog.setType(type);
      tenantCatalog.setCode(DefaultTenantCatalogs.ROOT_CODE);
      tenantCatalog.setName(DefaultTenantCatalogs.ROOT_NAME);
      tenantCatalog.setLevel(0);
      tenantCatalog.setPath("0");
      tenantCatalog.setParentId("0");
      tenantCatalog.setSort(0);
      BasicInterceptor.setCreateMsg(tenantCatalog);
      tenantCatalogMapper.insert(tenantCatalog);
    }
    return tenantCatalog;
  }
}
