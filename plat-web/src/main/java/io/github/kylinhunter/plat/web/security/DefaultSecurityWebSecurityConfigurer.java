package io.github.kylinhunter.plat.web.security;

import io.github.kylinhunter.plat.api.auth.context.UserContextHolder;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import io.github.kylinhunter.plat.web.security.error.DefaultAuthenticationEntryPoint;
import io.github.kylinhunter.plat.web.security.error.DefaultlAccessDeniedHandler;
import io.github.kylinhunter.plat.web.security.filter.JwtLoginFilter;
import io.github.kylinhunter.plat.web.security.filter.JwtVerifyFilter;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import io.github.kylinhunter.plat.web.trace.TraceHolder;
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
  protected UserContextHolder userContextHolder;

  @Autowired
  protected TraceHolder traceHolder;
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
    web.ignoring().antMatchers("/auth/verify_token", "/error");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = http.authorizeRequests();
    expressionInterceptUrlRegistry = addPerm(expressionInterceptUrlRegistry);
    expressionInterceptUrlRegistry.anyRequest().authenticated()
        .and().formLogin().loginProcessingUrl("/login")
        .and().logout().logoutUrl("/logout")
        .and().exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler(responseWriter))
        .authenticationEntryPoint(authenticationEntryPoint(responseWriter))
        .and()
        .addFilter(new JwtLoginFilter(authenticationManagerBean(), tokenService, responseWriter,
            traceHolder))
        .addFilter(new JwtVerifyFilter(authenticationManagerBean(), traceHolder, tokenService,
            userContextHolder, responseWriter))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors()
        .and().csrf().disable();


  }

  private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry addPerm(
      ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
    return expressionInterceptUrlRegistry;
  }


}