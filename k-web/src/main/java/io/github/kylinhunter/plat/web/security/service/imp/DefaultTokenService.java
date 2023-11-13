/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.web.security.service.imp;

import io.github.kylinhunter.commons.lang.EnumUtils;
import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.auth.TokenEx;
import io.github.kylinhunter.plat.api.auth.VerifyToken;
import io.github.kylinhunter.plat.api.auth.bean.vo.ReqTenantToken;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.github.kylinhunter.plat.api.module.core.redis.RedisKeys;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-10 02:45
 */
@RequiredArgsConstructor
@Slf4j
@ConditionalOnBean
public class DefaultTokenService implements TokenService {

  protected final JWTService jwtService;
  protected final RedisService redisService;

  @Override
  public String createToken(TokenUserDetails tokenUserDetails) {

    Token token = new Token();
    token.setUserId(tokenUserDetails.getUserId());
    token.setUserType(tokenUserDetails.getUserType());
    token.setNickName(tokenUserDetails.getNickName());
    token.setRealName(tokenUserDetails.getRealName());

    token.setUserName(tokenUserDetails.getUsername());
    token.setTenantId(tokenUserDetails.getTenantId());

    String tokenStr = jwtService.create(token);
    log.info("createToken user={} token={}", tokenUserDetails.getUsername(), tokenStr);
    return tokenStr;
  }

  @Override
  public String createTenantToken(ReqTenantToken reqTenantToken) {
    throw new AuthException("no implement");
  }

  @Override
  public Token invalidToken() {
    throw new AuthException("no implement");
  }

  /**
   * @param token token
   * @return io.github.kylinhunter.plat.core.service.local.security.bean.TokenUserDetails
   * @title verify
   * @description verify
   * @author BiJi'an
   * @date 2023-10-02 00:30
   */
  public TokenUserDetails verify(String token) {
    VerifyToken verifyToken = jwtService.verify(token);
    UserType userType = EnumUtils.fromCode(UserType.class, verifyToken.getUserType());
    TokenEx tokenEx;

    if (userType == UserType.USER || userType == UserType.SUPER_ADMIN) {
      tokenEx = getTokenEx(verifyToken.getUserId());
    } else {
      tokenEx = getTokenEx(verifyToken.getTenantUserId());
    }
    if (tokenEx == null) {
      throw new AuthException("token expired or logout");
    }

    return new TokenUserDetails(verifyToken, tokenEx.getPemCodes());
  }

  protected TokenEx getTokenEx(String userId) {
    return redisService.get(RedisKeys.AUTH_USER_PERMS.key(userId));
  }

  protected void removeTokenEx(Token token) {
    redisService.delete(RedisKeys.AUTH_USER_PERMS.key(token.getUserId()));
    redisService.delete(RedisKeys.AUTH_USER_PERMS.key(token.getTenantUserId()));
  }
}