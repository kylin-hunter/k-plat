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
package io.github.kylinhunter.plat.server.core.service.local.imp;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigVO;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import io.github.kylinhunter.plat.server.core.dao.mapper.TenantUserConfigMapper;
import io.github.kylinhunter.plat.server.core.service.local.TenantUserConfigService;
import io.github.kylinhunter.plat.server.core.service.local.interceptor.TenantUserConfigDeleteInterceptor;
import io.github.kylinhunter.plat.server.core.service.local.interceptor.TenantUserConfigFindByIdInterceptor;
import io.github.kylinhunter.plat.server.core.service.local.interceptor.TenantUserConfigSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.server.core.service.local.interceptor.TenentUserConfigQueryInterceptor;
import org.springframework.stereotype.Service;

/**
 * TenantUserConfigServiceImp 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Service
public class TenantUserConfigServiceImp
    extends CommonServiceImpl<
        TenantUserConfigMapper,
        TenantUserConfig,
        TenantUserConfigReqCreate,
        TenantUserConfigReqUpdate,
        TenantUserConfigResp,
        TenantUserConfigVO,
        TenantUserConfigReqQuery>
    implements TenantUserConfigService {

  public TenantUserConfigServiceImp(
      TenantUserConfigDeleteInterceptor tenantUserConfigDeleteInterceptor,
      TenantUserConfigSaveOrUpdateInterceptor tenantUserConfigSaveOrUpdateInterceptor,
      TenantUserConfigFindByIdInterceptor tenantUserConfigFindByIdInterceptor,
      TenentUserConfigQueryInterceptor tenantUserConfigQueryInterceptor) {
    this.deleteInterceptor = tenantUserConfigDeleteInterceptor;
    this.saveOrUpdateInterceptor = tenantUserConfigSaveOrUpdateInterceptor;
    this.findByIdInterceptor = tenantUserConfigFindByIdInterceptor;
    this.queryInterceptor = tenantUserConfigQueryInterceptor;
  }
}
