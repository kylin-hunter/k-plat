package io.github.kylinhunter.plat.core.security.service.imp;

import com.google.common.collect.Sets;
import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.commons.lang.EnumUtils;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.github.kylinhunter.plat.core.service.local.RoleService;
import io.github.kylinhunter.plat.core.service.local.TenantRoleService;
import io.github.kylinhunter.plat.core.service.local.TenantUserService;
import io.github.kylinhunter.plat.core.service.local.UserService;
import io.github.kylinhunter.plat.api.module.core.redis.RedisKeys;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import io.github.kylinhunter.plat.web.exception.WebErrInfoCustomizer;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.TenantUserDetailsService;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 00:23
 */
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService, TenantUserDetailsService {


  private final UserService userService;
  private final TenantUserService tenantUserService;

  private final RoleService roleService;
  private final TenantRoleService tenantRoleService;

  private final RedisService redisService;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.findByUserCode(username);
    if (Objects.isNull(user)) {
      throw new BizException(WebErrInfoCustomizer.AUTH_ERROR, "username or password error");
    }
    List<Permission> permissions = roleService.findPermissionsByUserId(user.getId());
    calPermCodes(permissions, user.getId(), user.getType());

    return new TokenUserDetails(user);


  }


  @Override
  public UserDetails loadTenantUserByUsername(String tenantId, String username)
      throws UsernameNotFoundException {
    User user = userService.findByUserCode(username);
    if (Objects.isNull(user)) {
      throw new BizException(WebErrInfoCustomizer.AUTH_ERROR, "username or password error");
    }

    TenantUser tenantUser = tenantUserService.findByTenantAndUser(tenantId, user.getId());
    if (Objects.isNull(tenantUser)) {
      throw new BizException(WebErrInfoCustomizer.AUTH_ERROR, "tenant user not exist");
    }

    List<Permission> permissions = tenantRoleService.findPermissionsByUserId(tenantId,
        tenantUser.getId());
    calPermCodes(permissions, tenantUser.getId(), tenantUser.getType());

    return new TokenUserDetails(user, tenantUser);
  }

  private void calPermCodes(List<Permission> permissions, String userId, int userTypeCode) {
    Set<String> permCodes = Sets.newHashSet();

    if (!CollectionUtils.isEmpty(permissions)) {
      permCodes = permissions.stream().map(p -> p.getCode()).collect(Collectors.toSet());
    }
    UserType userType = EnumUtils.fromCode(UserType.class, userTypeCode);
    permCodes.add(userType.getName());
    redisService.set(RedisKeys.USER_PERMS.key(userId), permCodes);


  }
}