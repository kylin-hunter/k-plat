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
package io.github.kylinhunter.plat.core.init.initializer;

import io.github.kylinhunter.plat.api.auth.context.UserContextHolder;
import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.github.kylinhunter.plat.core.init.data.TenantInitDatas;
import io.github.kylinhunter.plat.core.service.local.TenantService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:53
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Getter
@Setter
public class Order03TenantInitializer extends BasicInitializer {

  private final TenantService tenantService;
  private final TenantInitDatas tenantInitData;
  private final UserContextHolder userContextHolder;

  @Override
  public void init() {

    TenantReqCreate defaultTenant = tenantInitData.getDefaultTenant();
    String code = defaultTenant.getCode();
    Tenant tenant = tenantService.queryByCode(code);
    if (tenant != null) {
      log.info("default agent exist");
      tenantInitData.addDbData(code, tenant);

    } else {
      tenantService.save(defaultTenant);
      log.info("default agent created");

      tenant = tenantService.queryByCode(code);
      tenantInitData.addDbData(code, tenant);
    }

    UserContext userContext = userContextHolder.get();
    userContext.setTenantId(tenant.getId());
    userContext.setUserType(UserType.TENANT_ADMIN.getCode());
  }
}
