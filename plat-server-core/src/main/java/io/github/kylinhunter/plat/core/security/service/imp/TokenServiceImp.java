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
package io.github.kylinhunter.plat.core.security.service.imp;

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.auth.TokenEx;
import io.github.kylinhunter.plat.api.auth.VerifyToken;
import io.github.kylinhunter.plat.api.auth.bean.vo.ReqTenantToken;
import io.github.kylinhunter.plat.api.module.core.redis.RedisKeys;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.config.KplatConfig;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.TenantUserDetailsService;
import io.github.kylinhunter.plat.web.security.service.imp.DefaultTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-10 02:45
 */
@Slf4j
public class TokenServiceImp extends DefaultTokenService {

  private final TraceHolder traceHolder;

  private final TenantUserDetailsService tenantUserDetailsService;

  private final KplatConfig kplatConfig;

  public TokenServiceImp(
      KplatConfig kplatConfig,
      JWTService jwtService,
      TraceHolder traceHolder,
      RedisService redisService,
      TenantUserDetailsService tenantUserDetailsService) {
    super(jwtService, redisService);
    this.kplatConfig = kplatConfig;
    this.traceHolder = traceHolder;
    this.tenantUserDetailsService = tenantUserDetailsService;
  }

  /**
   * @param tokenUserDetails tokenUserDetails
   * @return java.lang.String
   * @title createToken
   * @description createToken
   * @author BiJi'an
   * @date 2023-10-02 00:29
   */
  @Override
  public String createToken(TokenUserDetails tokenUserDetails) {

    Token token = new Token();
    token.setUserId(tokenUserDetails.getUserId());
    token.setUserType(tokenUserDetails.getUserType());
    token.setNickName(tokenUserDetails.getNickName());
    token.setRealName(tokenUserDetails.getRealName());
    token.setUserName(tokenUserDetails.getUsername());
    token.setEffectiveTime(kplatConfig.getTokenExpireTime());

    String tenantId = tokenUserDetails.getTenantId();
    if (!StringUtils.isEmpty(tenantId)) {
      tokenUserDetails =
          tenantUserDetailsService.loadTenantUserByUsername(tenantId, token.getUserName());
      token.setTenantId(tenantId);
      token.setUserType(tokenUserDetails.getUserType());
      token.setTenantUserId(tokenUserDetails.getTenantUserId());
      setTokenEx(token.getTenantUserId(), new TokenEx(tokenUserDetails.getPemCodes()));

    } else {
      setTokenEx(token.getUserId(), new TokenEx(tokenUserDetails.getPemCodes()));
    }

    String tokenStr = jwtService.create(token);
    log.info("createToken user={} token={}", tokenUserDetails.getUsername(), tokenStr);

    return tokenStr;
  }

  /**
   * @param reqTenantToken reqTenantToken
   * @return java.lang.String
   * @title createTenantToken
   * @description createTenantToken
   * @author BiJi'an
   * @date 2023-10-02 00:05
   */
  @Override
  public String createTenantToken(ReqTenantToken reqTenantToken) {
    Token token = traceHolder.get().getVerifyToken();
    token.setEffectiveTime(kplatConfig.getTokenExpireTime());
    String tenantId = reqTenantToken.getTenantId();
    TokenUserDetails userDetails =
        tenantUserDetailsService.loadTenantUserByUsername(tenantId, token.getUserName());

    token.setTenantId(tenantId);
    token.setUserType(userDetails.getUserType());
    token.setTenantUserId(userDetails.getTenantUserId());
    String tokenStr = jwtService.create(token);
    log.info(
        "create tenant={},username={},token={}", tenantId, userDetails.getUsername(), tokenStr);

    setTokenEx(token.getTenantUserId(), new TokenEx(userDetails.getPemCodes()));

    return tokenStr;
  }

  @Override
  public Token invalidToken() {
    String token = traceHolder.get().getToken();
    if (StringUtils.isNotBlank(token)) {
      VerifyToken verifyToken = jwtService.verify(token);
      removeTokenEx(verifyToken);
      return verifyToken;
    } else {
      return null;
    }
  }

  /**
   * @param userId  userId
   * @param tokenEx tokenEx
   * @return void
   * @throws
   * @title setTokenEx
   * @description setTokenEx
   * @author BiJi'an
   * @date 2023-10-16 10:55
   */
  protected void setTokenEx(String userId, TokenEx tokenEx) {
    redisService.set(RedisKeys.AUTH_USER_PERMS.key(userId), tokenEx,
        kplatConfig.getTokenExpireTime());
  }

}
