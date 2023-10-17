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
package io.github.kylinhunter.plat.core.security;

import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.core.security.service.imp.TokenServiceImp;
import io.github.kylinhunter.plat.core.security.service.imp.UserDetailsServiceImp;
import io.github.kylinhunter.plat.core.service.local.RoleService;
import io.github.kylinhunter.plat.core.service.local.TenantRoleService;
import io.github.kylinhunter.plat.core.service.local.TenantService;
import io.github.kylinhunter.plat.core.service.local.TenantUserService;
import io.github.kylinhunter.plat.core.service.local.UserService;
import io.github.kylinhunter.plat.data.redis.configuration.RedisTemplateConfiguration;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.config.KplatConfig;
import io.github.kylinhunter.plat.web.security.service.TenantUserDetailsService;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 00:27
 */
@Configuration
@AutoConfigureAfter(RedisTemplateConfiguration.class)
@RequiredArgsConstructor
public class SecurityConfiguration {

  @Bean
  public TokenService tokenService(KplatConfig kplatConfig,
      JWTService jwtService,
      TraceHolder traceHolder,
      RedisService redisService,
      TenantUserDetailsService tenantUserDetailsService) {
    return new TokenServiceImp(kplatConfig, jwtService, traceHolder, redisService,
        tenantUserDetailsService);
  }

  @Bean
  public UserDetailsService userDetailsService(
      UserService userService,
      TenantUserService tenantUserService,
      RoleService roleService,
      TenantService tenantService,
      TenantRoleService tenantRoleService,
      RedisService redisService) {
    return new UserDetailsServiceImp(
        userService,
        roleService,
        tenantService,
        tenantUserService,
        tenantRoleService,
        redisService);
  }

  @Bean
  public TenantUserDetailsService tenantUserDetailsService(
      UserService userService,
      TenantUserService tenantUserService,
      RoleService roleService,
      TenantService tenantService,
      TenantRoleService tenantRoleService,
      RedisService redisService) {
    return new UserDetailsServiceImp(
        userService,
        roleService,
        tenantService,
        tenantUserService,
        tenantRoleService,
        redisService);
  }

}
