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
package io.github.kylinhunter.plat.core.init.data;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;
import java.util.UUID;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 */
@Component
@Getter
public class TenantRoleInitDatas extends BasicInitDatas<TenantRoleReqCreate, TenantRole> {
  public static final String ROLE_ADMIN_CODE = "tenant_admin";
  public static final String ROLE_TEST_CODE = "tenant_test";

  private TenantRoleReqCreate roleAdmin = createRoleAdmin();
  private TenantRoleReqCreate roleTest = createRoleTest();

  private TenantRoleReqCreate createRoleAdmin() {

    TenantRoleReqCreate roleReqCreate = new TenantRoleReqCreate();
    roleReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
    roleReqCreate.setCode(ROLE_ADMIN_CODE);
    roleReqCreate.setName(ROLE_ADMIN_CODE);
    roleReqCreate.setType(0);
    roleReqCreate.setStatus(0);
    roleReqCreate.setDescription(ROLE_ADMIN_CODE);
    this.addInitData(roleReqCreate.getCode(), roleReqCreate);
    return roleReqCreate;
  }

  private TenantRoleReqCreate createRoleTest() {

    TenantRoleReqCreate roleReqCreate = new TenantRoleReqCreate();
    roleReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
    roleReqCreate.setCode(ROLE_TEST_CODE);
    roleReqCreate.setName(ROLE_TEST_CODE);
    roleReqCreate.setType(0);
    roleReqCreate.setStatus(0);
    roleReqCreate.setDescription(ROLE_TEST_CODE);
    this.addInitData(roleReqCreate.getCode(), roleReqCreate);
    return roleReqCreate;
  }
}
