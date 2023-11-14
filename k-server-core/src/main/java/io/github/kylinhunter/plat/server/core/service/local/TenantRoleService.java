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
package io.github.kylinhunter.plat.server.core.service.local;

import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * TenantRoleService 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-17
 */
public interface TenantRoleService
    extends CommonService<
        TenantRole,
        TenantRoleReqCreate,
        TenantRoleReqUpdate,
        TenantRoleResp,
        TenantRoleVO,
        TenantRoleReqQuery> {

  TenantRole queryByCode(String code);

  @Select(
      "SELECT t3.*  FROM  kplat_tenant_user_role t1,kplat_tenant_role_permission t2,kplat_permission t3  "
          + "  WHERE t1.role_id=t2.role_id and t2.permission_id=t3.id"
          + "  and t1.sys_tenant_id = #{tenantId} and t1.user_id = #{userId}")
  List<Permission> findPermissionsByUserId(
      @Param("tenantId") String tenantId, @Param("userId") String userId);
}
