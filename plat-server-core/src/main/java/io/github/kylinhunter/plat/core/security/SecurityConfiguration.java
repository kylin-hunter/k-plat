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
  public TokenService tokenService(JWTService jwtService,
      TraceHolder traceHolder, RedisService redisService,
      TenantUserDetailsService tenantUserDetailsService) {
    return new TokenServiceImp(jwtService,
        traceHolder, redisService, tenantUserDetailsService);
  }


  @Bean
  public UserDetailsService userDetailsService(UserService userService,
      TenantUserService tenantUserService,
      RoleService roleService, TenantService tenantService, TenantRoleService tenantRoleService,
      RedisService redisService) {
    return new UserDetailsServiceImp(userService, roleService,
        tenantService, tenantUserService, tenantRoleService, redisService);
  }

  @Bean
  public TenantUserDetailsService tenantUserDetailsService(UserService userService,
      TenantUserService tenantUserService,
      RoleService roleService, TenantService tenantService, TenantRoleService tenantRoleService,
      RedisService redisService) {
    return new UserDetailsServiceImp(userService, roleService,
        tenantService, tenantUserService, tenantRoleService, redisService);
  }

}