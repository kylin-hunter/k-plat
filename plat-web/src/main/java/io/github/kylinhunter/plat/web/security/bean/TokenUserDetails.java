package io.github.kylinhunter.plat.web.security.bean;

import io.github.kylinhunter.commons.lang.strings.StringUtil;
import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 00:24
 */
@Data
@NoArgsConstructor
public class TokenUserDetails implements UserDetails {

  private String username;
  private String password;
  private String id;
  private Integer type;

  private String tenantId;
  private String tenantUserId;
  private boolean accountNonExpired = true;
  private boolean accountNonLocked = true;
  private boolean credentialsNonExpired = true;
  private boolean enabled = true;
  private Token token;
  private User user;
  private TenantUser tenantUser;

  Collection<? extends GrantedAuthority> authorities;

  public TokenUserDetails(User user) {
    this(user, null, null);
  }

  public TokenUserDetails(User user, Set<String> pemCodes) {
    this(user, null, pemCodes);
  }

  public TokenUserDetails(User user, TenantUser tenantUser) {
    this(user, tenantUser, null);
  }


  public TokenUserDetails(User user, TenantUser tenantUser, Set<String> pemCodes) {
    this.user = user;
    this.tenantUser = tenantUser;

    this.username = user.getUserCode();
    this.password = user.getPassword();
    this.id = user.getId();
    this.type = user.getType();

    if (!CollectionUtils.isEmpty(pemCodes)) {
      authorities = pemCodes.stream().map(SimpleGrantedAuthority::new)
          .collect(Collectors.toList());
    } else {
      authorities = Collections.EMPTY_LIST;
    }

    if (tenantUser != null) {
      this.tenantId = tenantUser.getSysTenantId();
      this.tenantUserId = tenantUser.getId();
      this.type = tenantUser.getType();

    } else {
      this.tenantId = StringUtil.EMPTY;
    }
  }

  public TokenUserDetails(Token token, Set<String> pemCodes) {
    this.token = token;

    this.username = token.getUserCode();
    this.password = "";
    this.id = token.getUserId();
    this.type = token.getUserType();

    if (!CollectionUtils.isEmpty(pemCodes)) {
      authorities = pemCodes.stream().map(SimpleGrantedAuthority::new)
          .collect(Collectors.toList());
    } else {
      authorities = Collections.EMPTY_LIST;
    }

    this.tenantId = token.getTenantId();

  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }


}