package io.github.kylinhunter.plat.core.security;

import io.github.kylinhunter.plat.core.dao.mapper.TenantMapper;
import io.github.kylinhunter.plat.core.dao.mapper.UserMapper;
import io.github.kylinhunter.plat.core.security.service.imp.TokenServiceImp;
import io.github.kylinhunter.plat.core.security.service.imp.UserDetailsServiceImp;
import io.github.kylinhunter.plat.core.service.local.TenantUserService;
import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 00:27
 */
@Configuration
public class SecurityConfiguration  {



  @Bean
  public TokenService tokenService(TenantMapper tenantMapper, JWTService jwtService,
      TenantUserService tenantUserService) {
    return new TokenServiceImp(tenantMapper, jwtService, tenantUserService);
  }

  @Bean
  public UserDetailsService userDetailsService(UserMapper userMapper) {
    return new UserDetailsServiceImp(userMapper);
  }


}