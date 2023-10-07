package io.github.kylinhunter.plat.core.security;

import io.github.kylinhunter.plat.web.security.DefaultSecurityWebSecurityConfigurer;
import io.github.kylinhunter.plat.web.security.filter.JwtLoginFilter;
import io.github.kylinhunter.plat.web.security.filter.JwtVerifyFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 00:27
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityWebSecurityConfigurer extends DefaultSecurityWebSecurityConfigurer {

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/initialize",
        "/auth/verify_token",
        "/health",
        "/echo/**",
        "/error");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = http.authorizeRequests();
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry);
    expressionInterceptUrlRegistry.anyRequest().authenticated()
        .and().formLogin().loginProcessingUrl("/login")
        .and().logout().logoutUrl("/logout")
        .and().exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler(responseWriter))
        .authenticationEntryPoint(authenticationEntryPoint(responseWriter))
        .and()
        .addFilter(new JwtLoginFilter(authenticationManagerBean(), tokenService, responseWriter))
        .addFilter(new JwtVerifyFilter(authenticationManagerBean(), traceHandler, tokenService,
            userContextHandler, responseWriter))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors()
        .and().csrf().disable();


  }

  private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry addPermission(
      ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "users");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "roles");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "permissions");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "role_permissions");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "sys_configs");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "sys_user_configs");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "tenant_catalogs");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "tenant_configs");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "tenants");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "tenant_roles");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "tenant_user_configs");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "tenant_users");
    expressionInterceptUrlRegistry = addPermission(expressionInterceptUrlRegistry, "user_roles");
    return expressionInterceptUrlRegistry;
  }

  private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry addPermission(
      ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry,
      String module) {
    String baseUrl = "/api/v1/core/" + module;

    expressionInterceptUrlRegistry = expressionInterceptUrlRegistry

        .antMatchers(HttpMethod.POST, baseUrl)
        .hasAuthority(module + "::create")

        .antMatchers(HttpMethod.PUT, baseUrl + "/*")
        .hasAuthority(module + "::update")

        .antMatchers(HttpMethod.DELETE, baseUrl + "/*")
        .hasAuthority(module + "::delete")

        .antMatchers(HttpMethod.DELETE, baseUrl + "/batch")
        .hasAuthority(module + "::batch_delete")

        .antMatchers(HttpMethod.GET, baseUrl + "/*")
        .hasAuthority(module + "::get")

        .antMatchers(HttpMethod.GET, baseUrl + "/batch")
        .hasAuthority(module + "::batch_get")
        .antMatchers(HttpMethod.GET, baseUrl)
        .hasAuthority(module + "::list");
    if (log.isInfoEnabled()) {
      log.info("add permission {}={}={}", "POST", baseUrl, module + "::create");
      log.info("add permission {}={}={}", "PUT", baseUrl + "/*", module + "::update");
      log.info("add permission {}={}={}", "DELETE", baseUrl + "/*", module + "::delete");
      log.info("add permission {}={}={}", "DELETE", baseUrl + "/batch", module + "::batch_delete");
      log.info("add permission {}={}={}", "GET", baseUrl + "/*", module + "::get");
      log.info("add permission {}={}={}", "GET", baseUrl + "/batch", module + "::batch_get");
      log.info("add permission {}={}={}", "GET", baseUrl, module + "::list");

    }
    return expressionInterceptUrlRegistry;
  }


}