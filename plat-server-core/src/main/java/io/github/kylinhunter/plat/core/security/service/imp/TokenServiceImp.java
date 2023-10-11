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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.kylinhunter.commons.lang.EnumUtils;
import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserResp;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.github.kylinhunter.plat.core.dao.mapper.TenantMapper;
import io.github.kylinhunter.plat.core.service.local.TenantUserService;
import io.github.kylinhunter.plat.api.module.core.redis.RedisKeys;
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
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-10 02:45
 */
@Slf4j
public class TokenServiceImp extends DefaultTokenService {

  private TenantMapper tenantMapper;
  private TenantUserService tenantUserService;

  private UserContextHandler userContextHandler;

  private RedisService redisService;

  private TenantUserDetailsService tenantUserDetailsService;

  @Value("${kplat.token_expire_time:1800}")
  private long tokenExpireTime;

  public TokenServiceImp(TenantMapper tenantMapper, JWTService jwtService,
      TenantUserService tenantUserService, UserContextHandler userContextHandler,
      RedisService redisService, TenantUserDetailsService tenantUserDetailsService) {
    super(jwtService);
    this.tenantMapper = tenantMapper;
    this.tenantUserService = tenantUserService;
    this.userContextHandler = userContextHandler;
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
  public String createToken(TokenUserDetails tokenUserDetails) {

    Token token = new Token();
    token.setUserId(tokenUserDetails.getUserId());
    token.setUserType(tokenUserDetails.getType());
    token.setNickName(tokenUserDetails.getNickName());
    token.setRealName(tokenUserDetails.getRealName());
    token.setUserName(tokenUserDetails.getUsername());
    token.setEffectiveTime(tokenExpireTime);

    String tenantId = tokenUserDetails.getTenantId();
    if (!StringUtils.isEmpty(tenantId)) {
      tokenUserDetails = tenantUserDetailsService.loadTenantUserByUsername(tenantId,
          token.getUserName());
      token.setTenantId(tenantId);
      token.setUserType(tokenUserDetails.getType());
      token.setTenantUserId(tokenUserDetails.getTenantUserId());
    }

    String tokenStr = jwtService.create(token);
    log.info("createToken user={} token={}", tokenUserDetails.getUsername(), tokenStr);
    redisService.set(RedisKeys.USER_PERMS.key(token.getUserId()), tokenUserDetails.getPemCodes(),
        tokenExpireTime);

    return tokenStr;

  }


  /**
   * @param loginToken loginToken
   * @param tenantId   tenantId
   * @return java.lang.String
   * @title createTenantToken
   * @description createTenantToken
   * @author BiJi'an
   * @date 2023-10-02 00:05
   */
  public String createTenantToken(String loginToken, String tenantId) {
    Token token = userContextHandler.get().getToken();

    TokenUserDetails userDetails = tenantUserDetailsService.loadTenantUserByUsername(tenantId,
        token.getUserName());

    token.setTenantId(tenantId);
    token.setUserType(userDetails.getType());
    token.setTenantUserId(userDetails.getTenantUserId());
    String tokenStr = jwtService.create(token);
    log.info("create tenant={},username={},token={}", tenantId, userDetails.getUsername(),
        tokenStr);
    redisService.set(RedisKeys.USER_PERMS.key(token.getTenantUserId()), userDetails.getPemCodes(),
        tokenExpireTime);

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
    Token verifyToken = jwtService.verify(token);
    UserType userType = EnumUtils.fromCode(UserType.class, verifyToken.getUserType());
    Set<String> pemCodes;

    if (userType == UserType.USER || userType == UserType.SUPER_ADMIN) {
      pemCodes = redisService.get(RedisKeys.USER_PERMS.key(verifyToken.getUserId()));
    } else {
      pemCodes = redisService.get(RedisKeys.USER_PERMS.key(verifyToken.getTenantUserId()));
    }
    if (Objects.isNull(pemCodes)) {
      throw new AuthException("no perms msg in redis");
    }

    return new TokenUserDetails(verifyToken, pemCodes);
  }

}
