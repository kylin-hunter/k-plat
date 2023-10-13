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

import io.github.kylinhunter.plat.api.module.core.bean.entity.UserRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleVO;
import io.github.kylinhunter.plat.core.dao.mapper.UserRoleMapper;
import io.github.kylinhunter.plat.core.service.local.UserRoleService;
import io.github.kylinhunter.plat.core.service.local.interceptor.UserRoleDeleteInterceptor;
import io.github.kylinhunter.plat.core.service.local.interceptor.UserRoleSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * UserRoleServiceImp 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Service
public class UserRoleServiceImp
    extends CommonServiceImpl<
        UserRoleMapper,
        UserRole,
        UserRoleReqCreate,
        UserRoleReqUpdate,
        UserRoleResp,
        UserRoleVO,
        UserRoleReqQuery>
    implements UserRoleService {

  public UserRoleServiceImp(
      UserRoleSaveOrUpdateInterceptor saveOrUpdateInterceptor,
      UserRoleDeleteInterceptor deleteInterceptor) {
    this.saveOrUpdateInterceptor = saveOrUpdateInterceptor;
    this.deleteInterceptor = deleteInterceptor;
  }
}
