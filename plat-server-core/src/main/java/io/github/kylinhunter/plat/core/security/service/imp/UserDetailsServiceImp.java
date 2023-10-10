package io.github.kylinhunter.plat.core.security.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.commons.lang.EnumUtils;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.bean.entity.UserRole;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.github.kylinhunter.plat.core.dao.mapper.RolePermissionMapper;
import io.github.kylinhunter.plat.core.dao.mapper.TenantUserMapper;
import io.github.kylinhunter.plat.core.dao.mapper.TenantUserRoleMapper;
import io.github.kylinhunter.plat.core.dao.mapper.UserMapper;
import io.github.kylinhunter.plat.core.dao.mapper.UserRoleMapper;
import io.github.kylinhunter.plat.core.service.local.UserRoleService;
import io.github.kylinhunter.plat.data.redis.RedisKeys;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import io.github.kylinhunter.plat.web.exception.WebErrInfoCustomizer;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.TenantUserDetailsService;
import java.util.Collections;
import java.util.HashMap;
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

  private final UserMapper userMapper;
  private final UserRoleMapper userRoleMapper;


  private final TenantUserMapper tenantUserMapper;
  private final TenantUserRoleMapper tenantUserRoleMapper;
  private final RedisService redisService;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(User::getUserCode, username);
    User user = userMapper.selectOne(wrapper);
    if (Objects.isNull(user)) {
      throw new BizException(WebErrInfoCustomizer.AUTH_ERROR, "username or password error");
    }
    Set<String> pemCodes = Sets.newHashSet();
    List<Permission> permissions = userRoleMapper.getPermissionsByUserId(user.getId());
    if (!CollectionUtils.isEmpty(permissions)) {
      pemCodes = permissions.stream().map(p -> p.getCode()).collect(Collectors.toSet());
    }
    UserType userType = EnumUtils.fromCode(UserType.class, user.getType());
    pemCodes.add(userType.getName());
    redisService.set(RedisKeys.USER_PERMS.key(user.getId()), pemCodes);

    return new TokenUserDetails(user);


  }

  @Override
  public UserDetails loadTenantUserByUsername(String tenantId, String username)
      throws UsernameNotFoundException {
    LambdaQueryWrapper<User> wrapperUser = new LambdaQueryWrapper<>();
    wrapperUser.eq(User::getUserCode, username);
    User user = userMapper.selectOne(wrapperUser);
    if (Objects.isNull(user)) {
      throw new BizException(WebErrInfoCustomizer.AUTH_ERROR, "username or password error");
    }

    LambdaQueryWrapper<TenantUser> wrapperTenantUser = new LambdaQueryWrapper<>();
    wrapperTenantUser.eq(TenantUser::getSysTenantId, tenantId);
    wrapperTenantUser.eq(TenantUser::getUserId, user.getId());
    TenantUser tenantUser = tenantUserMapper.selectOne(wrapperTenantUser);
    if (Objects.isNull(tenantUser)) {
      throw new BizException(WebErrInfoCustomizer.AUTH_ERROR, "tenant user not exist");
    }
    Set<String> pemCodes = Sets.newHashSet();
    List<Permission> permissions = tenantUserRoleMapper.getPermissionsByUserId(tenantId,
        user.getId());
    if (!CollectionUtils.isEmpty(permissions)) {
      pemCodes = permissions.stream().map(p -> p.getCode()).collect(Collectors.toSet());
    }
    UserType userType = EnumUtils.fromCode(UserType.class, tenantUser.getType());
    pemCodes.add(userType.getName());
    redisService.set(RedisKeys.USER_PERMS.key(tenantUser.getId()), pemCodes);

    return new TokenUserDetails(user);
  }
}