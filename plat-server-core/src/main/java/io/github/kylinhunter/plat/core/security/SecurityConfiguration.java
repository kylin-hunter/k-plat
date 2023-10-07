package io.github.kylinhunter.plat.core.security;

import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.core.dao.mapper.TenantMapper;
import io.github.kylinhunter.plat.core.dao.mapper.UserMapper;
import io.github.kylinhunter.plat.core.dao.mapper.UserRoleMapper;
import io.github.kylinhunter.plat.core.security.service.imp.TokenServiceImp;
import io.github.kylinhunter.plat.core.security.service.imp.UserDetailsServiceImp;
import io.github.kylinhunter.plat.core.service.local.TenantUserService;
import io.github.kylinhunter.plat.data.redis.configuration.RedisTemplateConfiguration;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
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
      TenantUserService tenantUserService, UserContextHandler userContextHandler,RedisService redisService) {
    return new TokenServiceImp(tenantMapper, jwtService, tenantUserService, userContextHandler,redisService);
  }

  @Bean
  public UserDetailsService userDetailsService(UserMapper userMapper, UserRoleMapper userRoleMapper,RedisService redisService) {
    return new UserDetailsServiceImp(userMapper, userRoleMapper,redisService);
  }


}