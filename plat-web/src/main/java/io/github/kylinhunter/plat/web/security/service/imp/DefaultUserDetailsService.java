package io.github.kylinhunter.plat.web.security.service.imp;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.TenantUserDetailsService;
import java.util.Collections;
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
    User user = new User();
    user.setUserName(username);
    return new TokenUserDetails(user, Collections.EMPTY_SET);
  }

  @Override
  public TokenUserDetails loadTenantUserByUsername(String tenantId, String username)
      throws UsernameNotFoundException {
    User user = new User();
    user.setUserName(username);
    TenantUser tenantUser = new TenantUser();

    return new TokenUserDetails(user, tenantUser, Collections.EMPTY_SET);
  }
}