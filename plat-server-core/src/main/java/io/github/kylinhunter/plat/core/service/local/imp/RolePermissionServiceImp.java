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

import io.github.kylinhunter.plat.api.module.core.bean.entity.RolePermission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionVO;
import io.github.kylinhunter.plat.core.dao.mapper.RolePermissionMapper;
import io.github.kylinhunter.plat.core.service.local.RolePermissionService;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * RolePermissionServiceImp 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Service
public class RolePermissionServiceImp
    extends CommonServiceImpl<
        RolePermissionMapper,
        RolePermission,
        RolePermissionReqCreate,
        RolePermissionReqUpdate,
        RolePermissionResp,
        RolePermissionVO,
        RolePermissionReqQuery>
    implements RolePermissionService {}
