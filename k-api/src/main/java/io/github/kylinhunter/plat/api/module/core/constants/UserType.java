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
package io.github.kylinhunter.plat.api.module.core.constants;

import io.github.kylinhunter.commons.lang.EnumUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-07 16:34
 */
@Getter
@RequiredArgsConstructor
public enum UserType implements EnumUtils.EnumCode {
  USER(0, "user"),
  TENANT_USER(1, "tenantUser"),

  SUPER_ADMIN(100, "superAdmin"),
  TENANT_ADMIN(99, "tenantAdmin");
  private final int code;
  private final String name;

  public static boolean isSuperAdmin(int type) {
    return type == 1;
  }

  public static boolean isTenantAdmin(int type) {
    return type == 3;
  }
}