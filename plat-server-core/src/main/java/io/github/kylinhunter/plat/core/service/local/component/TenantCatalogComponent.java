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
import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreateBatch;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogRespTree;
import io.github.kylinhunter.plat.api.module.core.constants.init.DefaultTenantCatalogs;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.core.dao.mapper.TenantCatalogMapper;
import io.github.kylinhunter.plat.dao.service.local.interceptor.BasicInterceptor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

  private void trySave(TenantCatalog curtCatalog, TenantCatalog parent) {
    if (parent == null) {
      throw new InitException("parent can't be   null ");
    }
    int type = curtCatalog.getType();
    String code = curtCatalog.getCode();
    String tenantId = TraceHolder.get().getUserContext().getTenantId();

    TenantCatalog catalog = tenantCatalogMapper.findByCode(tenantId, type, code);

    if (catalog != null) {
      catalog.setName(curtCatalog.getName());
      catalog.setParentId(parent.getId());

      catalog.setLevel(parent.getLevel() + 1);
      catalog.setPath(parent.getPath() + "_" + parent.getId());
      catalog.setSort(curtCatalog.getSort());


      BasicInterceptor.setUpdateMsg(catalog);
      tenantCatalogMapper.updateById(catalog);
      log.info("update catalog={}", catalog);

    } else {
      catalog = new TenantCatalog();
      catalog.setType(type);
      catalog.setCode(code);
      catalog.setName(curtCatalog.getName());
      catalog.setParentId(parent.getId());
      catalog.setLevel(parent.getLevel() + 1);
      catalog.setPath(parent.getPath() + "_" + parent.getId());
      catalog.setSort(curtCatalog.getSort());

      BasicInterceptor.setCreateMsg(catalog);
      tenantCatalogMapper.insert(catalog);
      log.info("create catalog={}", code);
    }
  }

}
