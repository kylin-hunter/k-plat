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
package io.github.kylinhunter.plat.search.security;

import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.github.kylinhunter.plat.web.security.DefaultSecurityWebSecurityConfigurer;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

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
  protected ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
      addPerm(
          ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
              urlRegistry) {
    String[] defaultAuthorities =
        new String[] {UserType.SUPER_ADMIN.getName(), UserType.TENANT_ADMIN.getName()};

    urlRegistry = addPerm(urlRegistry, "file_relations", defaultAuthorities);
    urlRegistry = addPerm(urlRegistry, "file_metadatas", defaultAuthorities);

    urlRegistry
        .antMatchers(HttpMethod.POST, "/api/v1/storage/upload")
        .hasAnyAuthority(ArrayUtils.add(defaultAuthorities, "storage_upload"));

    urlRegistry
        .antMatchers(HttpMethod.GET, "/api/v1/storage/download")
        .hasAnyAuthority(ArrayUtils.add(defaultAuthorities, "storage_download"));

    urlRegistry
        .antMatchers(HttpMethod.GET, "/api/v1/storage/exists")
        .hasAnyAuthority(ArrayUtils.add(defaultAuthorities, "storage_exist"));

    urlRegistry
        .antMatchers(HttpMethod.DELETE, "/api/v1/storage/delete")
        .hasAnyAuthority(ArrayUtils.add(defaultAuthorities, "storage_delete"));
    return urlRegistry;
  }

  private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry addPerm(
      ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
          expressionInterceptUrlRegistry,
      String module,
      String[] authorities) {
    String baseUrl = "/api/v1/storage/" + module;
    String[] createAuthorities = ArrayUtils.add(authorities, module + "::create");
    String[] updateAuthorities = ArrayUtils.add(authorities, module + "::update");
    String[] deleteAuthorities = ArrayUtils.add(authorities, module + "::delete");
    String[] getAuthorities = ArrayUtils.add(authorities, module + "::get");

    expressionInterceptUrlRegistry =
        expressionInterceptUrlRegistry
            .antMatchers(HttpMethod.POST, baseUrl)
            .hasAnyAuthority(createAuthorities)
            .antMatchers(HttpMethod.PUT, baseUrl)
            .hasAnyAuthority(updateAuthorities)
            .antMatchers(HttpMethod.DELETE, baseUrl + "/*") // delete: /{id} or /batch
            .hasAnyAuthority(deleteAuthorities)
            .antMatchers(HttpMethod.GET, baseUrl, baseUrl + "/*") // get : /{id} or / or /batch
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
