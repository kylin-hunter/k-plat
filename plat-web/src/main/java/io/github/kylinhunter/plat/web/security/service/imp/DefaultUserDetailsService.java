package io.github.kylinhunter.plat.web.security.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
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
public class DefaultUserDetailsService implements UserDetailsService {


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(User::getUserName, username);
    User user = new User();
    user.setUserCode(username);
    return new TokenUserDetails(user, null);
  }
}