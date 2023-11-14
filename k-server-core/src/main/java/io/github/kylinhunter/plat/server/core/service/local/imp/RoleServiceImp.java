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
import io.github.kylinhunter.plat.api.module.core.bean.entity.Role;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleVO;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import io.github.kylinhunter.plat.server.core.dao.mapper.RoleMapper;
import io.github.kylinhunter.plat.server.core.dao.mapper.UserRoleMapper;
import io.github.kylinhunter.plat.server.core.service.local.RoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * RoleServiceImp 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-11
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImp
    extends CommonServiceImpl<
        RoleMapper, Role, RoleReqCreate, RoleReqUpdate, RoleResp, RoleVO, RoleReqQuery>
    implements RoleService {

  private final UserRoleMapper userRoleMapper;

  @Override
  public Role findByCode(String code) {
    LambdaQueryWrapper<Role> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.eq(Role::getSysDeleteFlag, false);
    queryWrapper.eq(Role::getCode, code);
    return this.baseMapper.selectOne(queryWrapper);
  }

  @Override
  public List<Permission> findPermissionsByUserId(String userId) {
    return userRoleMapper.findPermissionsByUserId(userId);
  }
}
