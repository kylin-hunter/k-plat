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

import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.github.kylinhunter.plat.core.init.data.TenantInitDatas;
import io.github.kylinhunter.plat.core.init.data.UserInitDatas;
import io.github.kylinhunter.plat.core.service.local.TenantService;
import io.github.kylinhunter.plat.core.service.local.TenantUserService;
import io.github.kylinhunter.plat.core.service.local.UserService;
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
public class Order04TenantUserInitializer extends BasicInitializer {

  private final TenantService tenantService;
  private final TenantInitDatas tenantInitData;

  private final UserService userService;
  private final UserInitDatas userInitData;

  private final TenantUserService tenantUserService;

  @Override
  public void init() {

    Tenant defaultTenant = tenantInitData.getDbData(TenantInitDatas.DEFAULT_CODE);

    addTenantUser(defaultTenant, UserInitDatas.USER_TEST, UserType.TENANT_USER);
    addTenantUser(defaultTenant, UserInitDatas.USER_ADMIN, UserType.TENANT_ADMIN);
  }

  private void addTenantUser(Tenant tenant, String userCode, UserType userType) {
    User user = userInitData.getDbData(userCode);

    final String tenantId = tenant.getId();
    final String userId = user.getId();
    TenantUser tenantUser = tenantUserService.findByTenantAndUser(tenantId, userId);
    if (tenantUser == null) {
      TenantUserReqCreate tenantUserReqCreate = new TenantUserReqCreate();
      tenantUserReqCreate.setTenantId(tenantId);
      tenantUserReqCreate.setUserId(userId);
      tenantUserReqCreate.setStatus(0);
      tenantUserReqCreate.setType(userType.getCode());
      tenantUserService.save(tenantUserReqCreate);
      log.info(
          "create tenant user tenant={},user={},type={}", tenant.getCode(), userCode, userType);
    } else {
      log.info(
          "exist tenant user tenant={},user={},type={}",
          tenant.getCode(),
          userCode,
          tenantUser.getType());
    }
  }
}
