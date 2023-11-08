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
package io.github.kylinhunter.plat.core.controller;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigVO;
import io.github.kylinhunter.plat.core.service.local.TenantConfigService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TenantConfigController 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/api/v1/core/tenant_configs")
@Api(value = "TenantConfig相关接口")
@RequiredArgsConstructor
public class TenantConfigController
    extends CommonCurdController<
        TenantConfigService,
        TenantConfigReqCreate,
        TenantConfigReqUpdate,
        TenantConfigResp,
        TenantConfigVO,
        TenantConfigReqQuery,
        TenantConfig> {}
