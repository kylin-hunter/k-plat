package io.github.kylinhunter.plat.web.configuration;

import io.github.kylinhunter.plat.web.auth.JWTService;
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
  @ConditionalOnMissingBean(TokenService.class)
  public UserDetailsService userDetailsService() {
    return new DefaultUserDetailsService();
  }


}