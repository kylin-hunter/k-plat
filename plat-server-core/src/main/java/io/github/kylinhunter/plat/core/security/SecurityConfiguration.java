package io.github.kylinhunter.plat.core.security;

import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.core.dao.mapper.TenantMapper;
import io.github.kylinhunter.plat.core.dao.mapper.TenantUserMapper;
import io.github.kylinhunter.plat.core.dao.mapper.TenantUserRoleMapper;
import io.github.kylinhunter.plat.core.dao.mapper.UserMapper;
import io.github.kylinhunter.plat.core.dao.mapper.UserRoleMapper;
import io.github.kylinhunter.plat.core.security.service.imp.TokenServiceImp;
import io.github.kylinhunter.plat.core.security.service.imp.UserDetailsServiceImp;
import io.github.kylinhunter.plat.core.service.local.RoleService;
import io.github.kylinhunter.plat.core.service.local.TenantRoleService;
import io.github.kylinhunter.plat.core.service.local.TenantUserService;
import io.github.kylinhunter.plat.core.service.local.UserService;
import io.github.kylinhunter.plat.data.redis.configuration.RedisTemplateConfiguration;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import io.github.kylinhunter.plat.web.auth.JWTService;
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
  public TokenService tokenService(TenantMapper tenantMapper, JWTService jwtService,
      TenantUserService tenantUserService, UserContextHandler userContextHandler,
      RedisService redisService,
      TenantUserDetailsService tenantUserDetailsService) {
    return new TokenServiceImp(tenantMapper, jwtService, tenantUserService, userContextHandler,
        redisService, tenantUserDetailsService);
  }


  @Bean
  public UserDetailsService userDetailsService(UserService userService,
      TenantUserService tenantUserService,
      RoleService roleService, TenantRoleService tenantRoleService,
      RedisService redisService) {
    return new UserDetailsServiceImp(userService, tenantUserService, roleService,
        tenantRoleService, redisService);
  }

  @Bean
  public TenantUserDetailsService tenantUserDetailsService(UserService userService,
      TenantUserService tenantUserService,
      RoleService roleService, TenantRoleService tenantRoleService,
      RedisService redisService) {
    return new UserDetailsServiceImp(userService, tenantUserService, roleService,
        tenantRoleService, redisService);
  }

}