package io.github.kylinhunter.plat.core.security.service;

import io.github.kylinhunter.plat.core.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.exception.AuthException;

/**
 * @author BiJi'an
 * @description TokenService
 * @date 2023-10-02 00:42
 */
public interface TokenService {

  String createToken(TokenUserDetails tokenUserDetails);

  String createTenantToken(String loginToken, String tenantId);

  TokenUserDetails verify(String token) throws AuthException;

}