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

import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import io.github.kylinhunter.plat.api.module.core.constants.TenantType;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 */
@Component
@Getter
public class TenantInitDatas extends BasicInitDatas<TenantReqCreate, Tenant> {
  public static final String DEFAULT_CODE = "default";
  public static final String DEFAULT_NAME = "DEFAULT";
  private TenantReqCreate defaultTenant = createDefaultTenant();

  private TenantReqCreate createDefaultTenant() {

    TenantReqCreate tenant = new TenantReqCreate();
    tenant.setCode(DEFAULT_CODE);
    tenant.setName(DEFAULT_NAME);
    tenant.setType(TenantType.SYS.getCode());
    tenant.setStatus(0);
    tenant.setDescription("默认租户");
    this.addInitData(tenant.getCode(), tenant);
    return tenant;
  }
}
