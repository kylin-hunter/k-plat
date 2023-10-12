package io.github.kylinhunter.plat.core.security;

import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.github.kylinhunter.plat.web.security.DefaultSecurityWebSecurityConfigurer;
import io.github.kylinhunter.plat.web.security.filter.JwtLoginFilter;
import io.github.kylinhunter.plat.web.security.filter.JwtVerifyFilter;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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

  private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry addPerm(
      ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry) {
    String[] defaultAuthorities = new String[]{UserType.SUPER_ADMIN.getName()};
    urlRegistry = addPerm(urlRegistry, "permissions", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "roles", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "role_permissions", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "users", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "user_roles", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "sys_configs", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "sys_user_configs", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "tenants", defaultAuthorities);
    defaultAuthorities = new String[]{UserType.SUPER_ADMIN.getName(),
        UserType.TENANT_ADMIN.getName()}
    ;

    urlRegistry = addPerm(urlRegistry, "tenant_configs", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "tenant_user_configs", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "tenant_catalogs", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "tenant_roles", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "tenant_role_permissions", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "tenant_users", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "tenant_user_roles", defaultAuthorities);

    return urlRegistry;
  }

  private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry addPerm(
      ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry,
      String module, String[] authorities) {
    String baseUrl = "/api/v1/core/" + module;
    String[] createAuthorities = ArrayUtils.add(authorities, module + "::create");
    String[] updateAuthorities = ArrayUtils.add(authorities, module + "::update");
    String[] deleteAuthorities = ArrayUtils.add(authorities, module + "::delete");
    String[] getAuthorities = ArrayUtils.add(authorities, module + "::get");

    expressionInterceptUrlRegistry = expressionInterceptUrlRegistry

        .antMatchers(HttpMethod.POST, baseUrl)
        .hasAnyAuthority(createAuthorities)

        .antMatchers(HttpMethod.PUT, baseUrl)
        .hasAnyAuthority(updateAuthorities)

        .antMatchers(HttpMethod.DELETE, baseUrl + "/*") // delete: /{id} or /batch
        .hasAnyAuthority(deleteAuthorities)

        .antMatchers(HttpMethod.GET, baseUrl, baseUrl + "/*")// get : /{id} or / or /batch
        .hasAnyAuthority(getAuthorities);

    if (log.isInfoEnabled()) {
      log.info("add authority {}={}={}", "POST", baseUrl, Arrays.toString(authorities));
      log.info("add authority {}={}={}", "PUT", baseUrl, Arrays.toString(authorities));
      log.info("add authority {}={}={}", "DELETE", baseUrl + "/*", Arrays.toString(authorities));
      log.info("add authority {}={}={}", "GET", baseUrl + "/*", Arrays.toString(authorities));

    }
    return expressionInterceptUrlRegistry;
  }


}