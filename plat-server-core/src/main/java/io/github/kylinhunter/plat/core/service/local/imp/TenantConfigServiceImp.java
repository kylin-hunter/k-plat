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

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigVO;
import io.github.kylinhunter.plat.core.dao.mapper.TenantConfigMapper;
import io.github.kylinhunter.plat.core.service.local.TenantConfigService;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * TenantConfigServiceImp 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Service
public class TenantConfigServiceImp
    extends CommonServiceImpl<
        TenantConfigMapper,
        TenantConfig,
        TenantConfigReqCreate,
        TenantConfigReqUpdate,
        TenantConfigResp,
        TenantConfigVO,
        TenantConfigReqQuery>
    implements TenantConfigService {}
