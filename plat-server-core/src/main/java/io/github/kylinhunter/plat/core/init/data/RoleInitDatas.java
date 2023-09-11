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

import io.github.kylinhunter.plat.api.module.core.bean.entity.Role;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;
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
public class RoleInitDatas extends BasicInitDatas<RoleReqCreate, Role> {
  public static final String Role_ADMIN_CODE = "admin";
  public static final String Role_TEST_CODE = "test";

  private RoleReqCreate RoleAdmin = createRoleAdmin();
  private RoleReqCreate RoleTest = createRoleTest();

  private RoleReqCreate createRoleAdmin() {

    RoleReqCreate roleReqCreate = new RoleReqCreate();
    roleReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
    roleReqCreate.setCode(Role_ADMIN_CODE);
    roleReqCreate.setName(Role_ADMIN_CODE);
    roleReqCreate.setType(0);
    roleReqCreate.setStatus(0);
    roleReqCreate.setDescription(Role_ADMIN_CODE);
    this.addInitData(roleReqCreate.getCode(), roleReqCreate);
    return roleReqCreate;
  }

  private RoleReqCreate createRoleTest() {

    RoleReqCreate roleReqCreate = new RoleReqCreate();
    roleReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
    roleReqCreate.setCode(Role_TEST_CODE);
    roleReqCreate.setName(Role_TEST_CODE);
    roleReqCreate.setType(0);
    roleReqCreate.setStatus(0);
    roleReqCreate.setDescription(Role_TEST_CODE);
    this.addInitData(roleReqCreate.getCode(), roleReqCreate);
    return roleReqCreate;
  }
}
