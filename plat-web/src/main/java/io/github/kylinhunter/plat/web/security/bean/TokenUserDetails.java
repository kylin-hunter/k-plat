package io.github.kylinhunter.plat.web.security.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.kylinhunter.commons.lang.strings.StringUtil;
import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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

  private String userId;

  private String nickName;
  private String realName;


  private Integer type;

  private String tenantId;
  private String tenantUserId;
  private boolean accountNonExpired = true;
  private boolean accountNonLocked = true;
  private boolean credentialsNonExpired = true;
  private boolean enabled = true;

  private Token token;
  @JsonIgnore
  private User user;
  @JsonIgnore
  private TenantUser tenantUser;

  @JsonIgnore
  private Set<String> pemCodes = new HashSet<>();
  Collection<? extends GrantedAuthority> authorities;


  public TokenUserDetails(User user, Set<String> pemCodes) {
    this(user, null, pemCodes);
  }


  public TokenUserDetails(User user, TenantUser tenantUser, Set<String> pemCodes) {
    this.user = user;
    this.tenantUser = tenantUser;

    this.username = user.getUserName();
    this.password = user.getPassword();
    this.userId = user.getId();
    this.nickName = user.getNickName();
    this.realName = user.getRealName();
    this.type = user.getType();

    if (tenantUser != null) {
      this.tenantId = tenantUser.getSysTenantId();
      this.tenantUserId = tenantUser.getId();
      this.type = tenantUser.getType();

    }
    this.pemCodes = pemCodes;
    if (!CollectionUtils.isEmpty(pemCodes)) {
      authorities = pemCodes.stream().map(SimpleGrantedAuthority::new)
          .collect(Collectors.toList());
    } else {
      authorities = Collections.emptyList();
    }
  }

  public TokenUserDetails(Token token, Set<String> pemCodes) {
    this.token = token;

    this.username = token.getUserName();
    this.password = "";
    this.userId = token.getUserId();
    this.nickName = token.getNickName();
    this.realName = token.getRealName();
    this.type = token.getUserType();

    if (!CollectionUtils.isEmpty(pemCodes)) {
      authorities = pemCodes.stream().map(SimpleGrantedAuthority::new)
          .collect(Collectors.toList());
    } else {
      authorities = Collections.emptyList();
    }

    this.tenantId = token.getTenantId();
    this.tenantUserId = token.getTenantUserId();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }


}