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
package io.github.kylinhunter.plat.web.configuration;

import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.security.service.TenantUserDetailsService;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import io.github.kylinhunter.plat.web.security.service.imp.DefaultTokenService;
import io.github.kylinhunter.plat.web.security.service.imp.DefaultUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 00:27
 */
@Configuration
public class AutoSecurityConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @ConditionalOnMissingBean(TokenService.class)
  public TokenService tokenService(JWTService jwtService) {
    return new DefaultTokenService(jwtService);
  }

  @Bean
  @ConditionalOnMissingBean(UserDetailsService.class)
  public UserDetailsService userDetailsService() {
    return new DefaultUserDetailsService();
  }

  @Bean
  @ConditionalOnMissingBean(UserDetailsService.class)
  public TenantUserDetailsService tenantUserDetailsService() {
    return new DefaultUserDetailsService();
  }
}
