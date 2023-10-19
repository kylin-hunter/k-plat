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
package io.github.kylinhunter.plat.web.security.service.imp;

import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.TenantUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 00:23
 */
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService, TenantUserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    throw new AuthException("no implement");
  }

  @Override
  public TokenUserDetails loadTenantUserByUsername(String tenantId, String username)
      throws UsernameNotFoundException {
    throw new AuthException("no implement");
  }
}
