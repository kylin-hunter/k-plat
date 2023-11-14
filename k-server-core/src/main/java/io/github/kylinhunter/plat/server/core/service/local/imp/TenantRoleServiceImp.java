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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleVO;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import io.github.kylinhunter.plat.server.core.dao.mapper.TenantRoleMapper;
import io.github.kylinhunter.plat.server.core.dao.mapper.TenantUserRoleMapper;
import io.github.kylinhunter.plat.server.core.service.local.TenantRoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * TenantRoleServiceImp 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Service
@RequiredArgsConstructor
public class TenantRoleServiceImp
    extends CommonServiceImpl<
        TenantRoleMapper,
        TenantRole,
        TenantRoleReqCreate,
        TenantRoleReqUpdate,
        TenantRoleResp,
        TenantRoleVO,
        TenantRoleReqQuery>
    implements TenantRoleService {

  private final TenantUserRoleMapper tenantUserRoleMapper;

  @Override
  public TenantRole queryByCode(String code) {
    LambdaQueryWrapper<TenantRole> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.eq(TenantRole::getSysDeleteFlag, false);
    queryWrapper.eq(TenantRole::getCode, code);
    return this.baseMapper.selectOne(queryWrapper);
  }

  @Override
  public List<Permission> findPermissionsByUserId(String tenantId, String userId) {
    return tenantUserRoleMapper.findPermissionsByUserId(tenantId, userId);
  }
}
