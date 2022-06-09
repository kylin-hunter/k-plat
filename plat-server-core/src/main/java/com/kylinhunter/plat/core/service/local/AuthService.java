package com.kylinhunter.plat.core.service.local;

import com.kylinhunter.plat.web.auth.LoginForm;
import com.kylinhunter.plat.web.auth.Token;

/**
 * <p>
 * UserService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
public interface AuthService {

    String login(LoginForm loginForm);

    Token verify(String token);
}