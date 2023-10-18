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
package io.github.kylinhunter.plat.core.service.local.interceptor;

import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import io.github.kylinhunter.plat.core.dao.mapper.TenantCatalogMapper;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.web.auth.PasswordManager;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 */
@Component
@RequiredArgsConstructor
public class TenantSaveOrUpdateInterceptor
    extends SaveOrUpdateInterceptor<
    Tenant, TenantReqCreate, TenantReqUpdate, TenantResp, TenantVO, TenantReqQuery> {

  public static final String DEFAULT_CODE = "default";
  public static final int DEFAULT_TYPE = 0;
  private final TenantCatalogMapper tenantCatalogMapper;

  @Override
  public TenantResp after(TenantReqCreate tenantReqCreate, Tenant enity, TenantResp response) {
    TenantResp tenantResp = super.after(tenantReqCreate, enity, response);

    TenantCatalog tenantCatalog = new TenantCatalog();
    tenantCatalog.setType(DEFAULT_TYPE);
    tenantCatalog.setCode(DEFAULT_CODE);
    tenantCatalog.setName(DEFAULT_CODE);
    tenantCatalog.setStatus(0);

    tenantCatalog.setLevel(0);
    tenantCatalog.setPath("0");
    tenantCatalog.setParentId("0");
    tenantCatalog.setDescription(DEFAULT_CODE);
    this.setCreateMsg((BaseEntity)tenantCatalog);
    tenantCatalog.setSysTenantId(enity.getId());
     tenantCatalogMapper.insert(tenantCatalog);
    return tenantResp;
  }
}
