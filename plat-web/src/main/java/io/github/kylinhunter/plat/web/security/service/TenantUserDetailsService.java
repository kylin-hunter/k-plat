package io.github.kylinhunter.plat.web.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author BiJi'an
 * @description TokenService
 * @date 2023-10-02 00:42
 */
public interface TenantUserDetailsService {

  UserDetails loadTenantUserByUsername(String tenantId, String username)
      throws UsernameNotFoundException;

}