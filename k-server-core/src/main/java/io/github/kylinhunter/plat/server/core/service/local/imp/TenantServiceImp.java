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

import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import io.github.kylinhunter.plat.server.core.dao.mapper.TenantMapper;
import io.github.kylinhunter.plat.server.core.service.local.TenantService;
import io.github.kylinhunter.plat.server.core.service.local.interceptor.TenantSaveOrUpdateInterceptor;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 * TenantServiceImp 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-12
 */
@Service
public class TenantServiceImp
    extends CommonServiceImpl<
        TenantMapper,
        Tenant,
        TenantReqCreate,
        TenantReqUpdate,
        TenantResp,
        TenantVO,
        TenantReqQuery>
    implements TenantService {

  public TenantServiceImp(TenantSaveOrUpdateInterceptor tenantSaveOrUpdateInterceptor) {
    this.saveOrUpdateInterceptor = tenantSaveOrUpdateInterceptor;
  }

  @PostConstruct
  @Override
  public void init() {
    super.init();
  }

  @Override
  public Tenant findByCode(String code) {
    return this.baseMapper.findByCode(code);
  }
}
