package io.github.kylinhunter.plat.web.security.service;

import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author BiJi'an
 * @description TokenService
 * @date 2023-10-02 00:42
 */
public interface TenantUserDetailsService {

  TokenUserDetails loadTenantUserByUsername(String tenantId, String username)
      throws UsernameNotFoundException;

}