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
package io.github.kylinhunter.plat.web.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.kylinhunter.commons.date.DateUtils;
import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.auth.VerifyToken;
import io.github.kylinhunter.plat.api.auth.context.DefaultUserContext;
import io.github.kylinhunter.plat.api.auth.context.UserContext;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.exception.WebErrInfos;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:47
 */
public class JWTService {

  private static final String USER_ID = "userId";
  private static final String TENANT_ID = "tenantId";

  private static final String TENANT_USER_ID = "tenantUserId";
  private static final String NICK_NAME = "nickName";
  private static final String REAL_NAME = "realName";
  private static final String USER_NAME = "userName";
  private static final String USER_TYPE = "userType";

  private static final String EFFECTIVE_TIME = "effectiveTime";

  private static final String SECRET = "kplat";

  private static final long MAX_EFFECTIVE_TIME = TimeUnit.DAYS.toSeconds(1);
  private static final long DEFAULT_EFFECTIVE_TIME = TimeUnit.SECONDS.toSeconds(30);

  public String create(Token tokenInfo) {
    try {
      LocalDateTime now = LocalDateTime.now();

      long effectiveTime = tokenInfo.getEffectiveTime();
      LocalDateTime expireTime;
      if (effectiveTime <= 0) {
        tokenInfo.setEffectiveTime(DEFAULT_EFFECTIVE_TIME);
        expireTime = now.plus(DEFAULT_EFFECTIVE_TIME, ChronoUnit.SECONDS);
      } else if (effectiveTime <= MAX_EFFECTIVE_TIME) {
        expireTime = now.plus(effectiveTime, ChronoUnit.SECONDS);
      } else {
        tokenInfo.setEffectiveTime(MAX_EFFECTIVE_TIME);
        expireTime = now.plus(MAX_EFFECTIVE_TIME, ChronoUnit.SECONDS);
      }
      tokenInfo.setExpireTime(expireTime);

      return JWT.create()
          //                .withHeader(map) // 添加头部
          .withClaim(TENANT_ID, tokenInfo.getTenantId()) // 添加payload
          .withClaim(TENANT_USER_ID, tokenInfo.getTenantUserId()) // 添加payload
          .withClaim(USER_ID, tokenInfo.getUserId()) // 添加payload
          .withClaim(NICK_NAME, tokenInfo.getNickName()) // 添加payload
          .withClaim(REAL_NAME, tokenInfo.getRealName()) // 添加payload
          .withClaim(USER_NAME, tokenInfo.getUserName())
          .withClaim(USER_TYPE, tokenInfo.getUserType())
          .withClaim(EFFECTIVE_TIME, tokenInfo.getEffectiveTime())
          .withExpiresAt(DateUtils.toDate(tokenInfo.getExpireTime())) // 设置过期时间
          .sign(Algorithm.HMAC256(SECRET));
    } catch (AuthException e) {
      throw e;
    } catch (Exception e) {
      throw new AuthException(WebErrInfos.AUTH_TOKEN_CREATE_ERROR, e);
    }
  }

  public VerifyToken verify(String token) {
    try {
      if (StringUtils.isBlank(token) || "null".equalsIgnoreCase(token)) {
        throw new AuthException(WebErrInfos.AUTH_TOKEN_VERIFY_NOT_FOUND);
      }
      JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
      UserContext userContext = new DefaultUserContext();
      DecodedJWT decodedJWT = jwtVerifier.verify(token);
      userContext.setUserType(decodedJWT.getClaim(USER_TYPE).asInt());

      userContext.setUserId(decodedJWT.getClaim(USER_ID).asString());
      userContext.setUserName(decodedJWT.getClaim(USER_NAME).asString());
      userContext.setNickName(decodedJWT.getClaim(NICK_NAME).asString());
      userContext.setRealName(decodedJWT.getClaim(REAL_NAME).asString());

      userContext.setTenantId(decodedJWT.getClaim(TENANT_ID).asString());
      userContext.setTenantUserId(decodedJWT.getClaim(TENANT_USER_ID).asString());

      long effectiveTime = decodedJWT.getClaim(EFFECTIVE_TIME).asLong();
      LocalDateTime expiredTime = DateUtils.toLocalDateTime(decodedJWT.getExpiresAt());
      return new VerifyToken(userContext, effectiveTime, expiredTime, token);
    } catch (AuthException e) {
      throw e;
    } catch (TokenExpiredException e) {
      throw new AuthException(WebErrInfos.AUTH_TOKEN_VERIFY_EXPIRED, e);
    } catch (JWTVerificationException e) {
      throw new AuthException(WebErrInfos.AUTH_TOKEN_VERIFY_INVALID, e);
    } catch (Exception e) {
      throw new AuthException(WebErrInfos.AUTH_TOKEN_VERIFY_ERROR, e);
    }
  }
}
