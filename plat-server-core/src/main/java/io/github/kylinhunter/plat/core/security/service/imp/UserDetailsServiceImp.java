package io.github.kylinhunter.plat.core.security.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.bean.entity.UserRole;
import io.github.kylinhunter.plat.core.dao.mapper.RolePermissionMapper;
import io.github.kylinhunter.plat.core.dao.mapper.UserMapper;
import io.github.kylinhunter.plat.core.dao.mapper.UserRoleMapper;
import io.github.kylinhunter.plat.core.service.local.UserRoleService;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import java.util.List;
import java.util.Objects;
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
public class UserDetailsServiceImp implements UserDetailsService {

  private final UserMapper userMapper;
  private final UserRoleMapper userRoleMapper;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(User::getUserName, username);
    User user = userMapper.selectOne(wrapper);
    if (Objects.isNull(user)) {
      throw new RuntimeException("username or password error");
    }
    List<Permission> permissions = userRoleMapper.getPermissionsByUserId(user.getId());

    return new TokenUserDetails(user);
  }
}