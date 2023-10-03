package io.github.kylinhunter.plat.web.security.bean;

import com.google.common.collect.Lists;
import io.github.kylinhunter.commons.lang.strings.StringUtil;
import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import java.util.Collection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
  private boolean accountNonExpired = true;
  private boolean accountNonLocked = true;
  private boolean credentialsNonExpired = true;
  private boolean enabled = true;
  private Token token;
  private User user;

  public TokenUserDetails(User user) {
    this(user, null);
  }

  public TokenUserDetails(User user, String tenantId) {
    this.username = user.getUserCode();
    this.password = user.getPassword();
    this.id = user.getId();
    this.type = user.getType();
    this.tenantId = StringUtil.defaultString(tenantId);
    this.user = user;
  }

  public TokenUserDetails(Token token) {
    this.username = token.getUserCode();
    this.password = "";
    this.id = token.getUserId();
    this.type = token.getUserType();
    this.tenantId = token.getTenantId();
    this.token = token;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Lists.newArrayList(new SimpleGrantedAuthority("list"));
  }


}