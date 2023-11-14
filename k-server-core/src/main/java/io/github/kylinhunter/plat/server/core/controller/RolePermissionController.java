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
package io.github.kylinhunter.plat.server.core.controller;

import io.github.kylinhunter.plat.api.module.core.bean.entity.RolePermission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionVO;
import io.github.kylinhunter.plat.server.core.service.local.RolePermissionService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RolePermissionController 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2023-10-07
 */
@RestController
@RequestMapping("/api/v1/core/role_permissions")
@Api(value = "RolePermission相关接口")
@RequiredArgsConstructor
public class RolePermissionController
    extends CommonCurdController<
        RolePermissionService,
        RolePermissionReqCreate,
        RolePermissionReqUpdate,
        RolePermissionResp,
        RolePermissionVO,
        RolePermissionReqQuery,
        RolePermission> {}
