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
package io.github.kylinhunter.plat.server.core.security.service.imp;

import com.google.common.collect.Sets;
import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.commons.lang.EnumUtils;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.github.kylinhunter.plat.server.core.service.local.RoleService;
import io.github.kylinhunter.plat.server.core.service.local.TenantRoleService;
import io.github.kylinhunter.plat.server.core.service.local.TenantService;
import io.github.kylinhunter.plat.server.core.service.local.TenantUserService;
import io.github.kylinhunter.plat.server.core.service.local.UserService;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.exception.WebErrInfos;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.TenantUserDetailsService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 00:23
 */
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImp implements UserDetailsService, TenantUserDetailsService {

  private final UserService userService;
  private final RoleService roleService;
  private final TenantService tenantService;
  private final TenantUserService tenantUserService;

  private final TenantRoleService tenantRoleService;

  private final RedisService redisService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.findByUserName(username);
    if (Objects.isNull(user)) {
      throw new BizException(WebErrInfos.AUTH_ERROR, "username or password error");
    }
    List<Permission> permissions = roleService.findPermissionsByUserId(user.getId());
    Set<String> permCodes = calPermCodes(permissions, user.getId(), user.getType());

    return new TokenUserDetails(user, permCodes);
  }

  @Override
  public TokenUserDetails loadTenantUserByUsername(String tenantId, String username)
      throws UsernameNotFoundException {
    User user = userService.findByUserName(username);
    if (Objects.isNull(user)) {
      throw new BizException(WebErrInfos.AUTH_ERROR, "username or password error");
    }
    Tenant tenant = tenantService.getById(tenantId);
    if (tenant == null) {
      throw new AuthException("tenant no exist");
    }
    TenantUser tenantUser = tenantUserService.findByTenantAndUser(tenantId, user.getId());
    if (Objects.isNull(tenantUser)) {

      UserType userType = EnumUtils.fromCode(UserType.class, user.getType());
      if (userType == UserType.SUPER_ADMIN) {
        tenantUser = addTenantUser(tenant, user);
      } else {
        throw new AuthException("user=" + user.getId() + " not in tenant=" + tenant.getId());
      }
    }

    List<Permission> permissions =
        tenantRoleService.findPermissionsByUserId(tenantId, tenantUser.getId());
    Set<String> permCodes = calPermCodes(permissions, tenantUser.getId(), tenantUser.getType());

    return new TokenUserDetails(user, tenantUser, permCodes);
  }

  private Set<String> calPermCodes(List<Permission> permissions, String userId, int userTypeCode) {
    Set<String> permCodes = Sets.newHashSet();

    if (!CollectionUtils.isEmpty(permissions)) {
      permCodes = permissions.stream().map(p -> p.getCode()).collect(Collectors.toSet());
    }
    UserType userType = EnumUtils.fromCode(UserType.class, userTypeCode);
    permCodes.add(userType.getName());
    return permCodes;
  }

  private TenantUser addTenantUser(Tenant tenant, User user) {

    TenantUser tenantUser = new TenantUser();

    tenantUser.setSysTenantId(tenant.getId());
    tenantUser.setSysCreatedUserId(user.getId());
    tenantUser.setSysCreatedUserName(user.getUserName());
    tenantUser.setSysCreatedTime(LocalDateTime.now());

    tenantUser.setSysUpdateUserId(user.getId());
    tenantUser.setSysUpdateUserName(user.getUserName());
    tenantUser.setSysUpdateTime(LocalDateTime.now());

    tenantUser.setSysDeleteFlag(false);

    tenantUser.setUserId(user.getId());
    tenantUser.setStatus(0);
    tenantUser.setType(UserType.TENANT_ADMIN.getCode());
    tenantUserService.save(tenantUser);
    log.info("create tenant admin tenantId={},user={}", tenant.getCode(), user.getUserName());
    return tenantUser;
  }
}
