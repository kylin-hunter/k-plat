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
package io.github.kylinhunter.plat.web.security;

import io.github.kylinhunter.plat.web.interceptor.PathPatterns;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import io.github.kylinhunter.plat.web.security.error.DefaultAuthenticationEntryPoint;
import io.github.kylinhunter.plat.web.security.error.DefaultlAccessDeniedHandler;
import io.github.kylinhunter.plat.web.security.filter.JwtLoginFilter;
import io.github.kylinhunter.plat.web.security.filter.JwtVerifyFilter;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 00:27
 */
public class DefaultSecurityWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

  @Autowired
  protected UserDetailsService userDetailsService;
  @Autowired
  protected TokenService tokenService;
  @Autowired
  protected PasswordEncoder passwordEncoder;

  @Autowired
  protected ResponseWriter responseWriter;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  public AccessDeniedHandler accessDeniedHandler(ResponseWriter responseWriter) {
    return new DefaultlAccessDeniedHandler(responseWriter);
  }

  public AuthenticationEntryPoint authenticationEntryPoint(ResponseWriter responseWriter) {
    return new DefaultAuthenticationEntryPoint(responseWriter);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(PathPatterns.of(PathPatterns.SECURITY_IGNORE))
        .antMatchers(PathPatterns.of(PathPatterns.ACTUATOR))
        .antMatchers(PathPatterns.of(PathPatterns.SWAGGER));
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
        expressionInterceptUrlRegistry = http.authorizeRequests();
    expressionInterceptUrlRegistry = addPerm(expressionInterceptUrlRegistry);
    expressionInterceptUrlRegistry
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginProcessingUrl("/login")
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(new DefaultLogoutSuccessHandler(tokenService, responseWriter))
        .and()
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler(responseWriter))
        .authenticationEntryPoint(authenticationEntryPoint(responseWriter))
        .and()

        .addFilterBefore(
            new JwtLoginFilter(
                authenticationManagerBean(), tokenService, responseWriter),
            UsernamePasswordAuthenticationFilter.class)
        .addFilter(
            new JwtVerifyFilter(
                authenticationManagerBean(), tokenService, responseWriter))
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors()
        .and()
        .csrf()
        .disable();
  }

  protected ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry addPerm(
      ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
          expressionInterceptUrlRegistry) {
    return expressionInterceptUrlRegistry;
  }
}
