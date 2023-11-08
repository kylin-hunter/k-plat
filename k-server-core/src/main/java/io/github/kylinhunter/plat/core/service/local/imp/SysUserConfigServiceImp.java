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
package io.github.kylinhunter.plat.core.service.local.imp;

import io.github.kylinhunter.plat.api.module.core.bean.entity.SysUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigVO;
import io.github.kylinhunter.plat.core.dao.mapper.SysUserConfigMapper;
import io.github.kylinhunter.plat.core.service.local.SysUserConfigService;
import io.github.kylinhunter.plat.core.service.local.interceptor.SysUserConfigDeleteInterceptor;
import io.github.kylinhunter.plat.core.service.local.interceptor.SysUserConfigFindByIdInterceptor;
import io.github.kylinhunter.plat.core.service.local.interceptor.SysUserConfigQueryInterceptor;
import io.github.kylinhunter.plat.core.service.local.interceptor.SysUserConfigSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * SysUserConfigServiceImp 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Service
public class SysUserConfigServiceImp
    extends CommonServiceImpl<
        SysUserConfigMapper,
        SysUserConfig,
        SysUserConfigReqCreate,
        SysUserConfigReqUpdate,
        SysUserConfigResp,
        SysUserConfigVO,
        SysUserConfigReqQuery>
    implements SysUserConfigService {

  public SysUserConfigServiceImp(
      SysUserConfigDeleteInterceptor sysUserConfigDeleteInterceptor,
      SysUserConfigSaveOrUpdateInterceptor sysUserConfigSaveOrUpdateInterceptor,
      SysUserConfigFindByIdInterceptor sysUserConfigFindByIdInterceptor,
      SysUserConfigQueryInterceptor sysUserConfigQueryInterceptor) {
    this.deleteInterceptor = sysUserConfigDeleteInterceptor;
    this.saveOrUpdateInterceptor = sysUserConfigSaveOrUpdateInterceptor;
    this.findByIdInterceptor = sysUserConfigFindByIdInterceptor;
    this.queryInterceptor = sysUserConfigQueryInterceptor;
  }
}
