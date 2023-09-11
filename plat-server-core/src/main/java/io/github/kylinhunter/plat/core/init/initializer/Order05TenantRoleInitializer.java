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

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import io.github.kylinhunter.plat.core.init.data.TenantRoleInitDatas;
import io.github.kylinhunter.plat.core.service.local.TenantRoleService;
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
public class Order05TenantRoleInitializer extends BasicInitializer {

  private final TenantRoleInitDatas tenantRoleInitData;
  private final TenantRoleService tenantRoleService;

  @Override
  public void init() {

    tenantRoleInitData
        .getInitDatas()
        .values()
        .forEach(
            tenantRoleCreate -> {
              final String roleCode = tenantRoleCreate.getCode();
              final TenantRole tenantRole = tenantRoleService.queryByCode(roleCode);
              if (tenantRole != null) {
                log.info("default role {} exist", roleCode);
                tenantRoleInitData.addDbData(roleCode, tenantRole);
              } else {
                tenantRoleService.save(tenantRoleCreate);
                log.info("default role {} created", roleCode);
                tenantRoleInitData.addDbData(roleCode, tenantRoleService.queryByCode(roleCode));
              }
            });
  }
}
