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

import io.github.kylinhunter.commons.lang.EnumUtils;
import io.github.kylinhunter.plat.api.auth.VerifyToken;
import io.github.kylinhunter.plat.api.auth.bean.vo.ReqTenantToken;
import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.github.kylinhunter.plat.api.module.core.redis.RedisKeys;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.TenantUserDetailsService;
import io.github.kylinhunter.plat.web.security.service.imp.DefaultTokenService;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-10 02:45
 */
@Slf4j
public class TokenServiceImp extends DefaultTokenService {


  private final TraceHolder traceHolder;
  private final RedisService redisService;

  private final TenantUserDetailsService tenantUserDetailsService;

  @Value("${kplat.token_expire_time:1800}")
  private long tokenExpireTime;

  public TokenServiceImp(JWTService jwtService, TraceHolder traceHolder,
      RedisService redisService, TenantUserDetailsService tenantUserDetailsService) {
    super(jwtService);
    this.traceHolder = traceHolder;
    this.redisService = redisService;
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
    token.setEffectiveTime(tokenExpireTime);

    String tenantId = tokenUserDetails.getTenantId();
    if (!StringUtils.isEmpty(tenantId)) {
      tokenUserDetails = tenantUserDetailsService.loadTenantUserByUsername(tenantId,
          token.getUserName());
      token.setTenantId(tenantId);
      token.setUserType(tokenUserDetails.getUserType());
      token.setTenantUserId(tokenUserDetails.getTenantUserId());
      setPerCodes(token.getTenantUserId(), tokenUserDetails.getPemCodes());

    } else {
      setPerCodes(token.getUserId(), tokenUserDetails.getPemCodes());

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
    token.setEffectiveTime(tokenExpireTime);
    String tenantId = reqTenantToken.getTenantId();
    TokenUserDetails userDetails = tenantUserDetailsService.loadTenantUserByUsername(tenantId,
        token.getUserName());

    token.setTenantId(tenantId);
    token.setUserType(userDetails.getUserType());
    token.setTenantUserId(userDetails.getTenantUserId());
    String tokenStr = jwtService.create(token);
    log.info("create tenant={},username={},token={}", tenantId, userDetails.getUsername(),
        tokenStr);

    setPerCodes(token.getTenantUserId(), userDetails.getPemCodes());

    return tokenStr;
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
    Set<String> pemCodes;

    if (userType == UserType.USER || userType == UserType.SUPER_ADMIN) {
      pemCodes = getPerCodes(verifyToken.getUserId());
    } else {
      pemCodes = getPerCodes(verifyToken.getTenantUserId());
    }
    if (Objects.isNull(pemCodes)) {
      throw new AuthException("no user permissions");
    }

    return new TokenUserDetails(verifyToken, pemCodes);
  }


  private void setPerCodes(String userId, Set<String> perCodes) {
    redisService.set(RedisKeys.AUTH_USER_PERMS.key(userId), perCodes, tokenExpireTime);
  }


  private Set<String> getPerCodes(String userId) {
    return redisService.get(RedisKeys.AUTH_USER_PERMS.key(userId));
  }
}
