package com.kylinhunter.plat.core.service.local;

import com.kylinhunter.plat.api.auth.ReqLogin;
import com.kylinhunter.plat.api.auth.Token;

/**
 * <p>
 * UserService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
public interface AuthService {

    String login(ReqLogin reqLogin);

    String createTenantToken(String token, String tenantId);

    Token verify(String token);
}