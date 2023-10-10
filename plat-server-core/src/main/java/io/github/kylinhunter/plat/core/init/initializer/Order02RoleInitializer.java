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

import io.github.kylinhunter.plat.api.module.core.bean.entity.Role;
import io.github.kylinhunter.plat.core.init.data.RoleInitDatas;
import io.github.kylinhunter.plat.core.service.local.RoleService;
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
@Setter
@Getter
public class Order02RoleInitializer extends BasicInitializer {
  private final RoleInitDatas roleInitData;
  private final RoleService roleService;

  @Override
  public void init() {

    roleInitData
        .getInitDatas()
        .values()
        .forEach(
            RoleCreate -> {
              final String RoleCode = RoleCreate.getCode();
              final Role Role = roleService.findByCode(RoleCode);
              if (Role != null) {
                log.info("default role {} exist", RoleCode);
                roleInitData.addDbData(RoleCode, Role);
              } else {
                roleService.save(RoleCreate);
                log.info("default role {} created", RoleCode);
                roleInitData.addDbData(RoleCode, roleService.findByCode(RoleCode));
              }
            });
  }
}
