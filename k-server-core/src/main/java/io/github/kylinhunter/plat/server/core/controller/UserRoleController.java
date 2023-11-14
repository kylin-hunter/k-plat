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

import io.github.kylinhunter.plat.api.module.core.bean.entity.UserRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleVO;
import io.github.kylinhunter.plat.server.core.service.local.UserRoleService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserRoleController 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2023-10-07
 */
@RestController
@RequestMapping("/api/v1/core/user_roles")
@Api(value = "UserRole相关接口")
@RequiredArgsConstructor
public class UserRoleController
    extends CommonCurdController<
    UserRoleService,
        UserRoleReqCreate,
        UserRoleReqUpdate,
        UserRoleResp,
        UserRoleVO,
        UserRoleReqQuery,
        UserRole> {}
