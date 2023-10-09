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
import io.github.kylinhunter.plat.data.redis.RedisKeys;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.imp.DefaultTokenService;
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

  private TenantMapper tenantMapper;
  private TenantUserService tenantUserService;

  private UserContextHandler userContextHandler;

  private RedisService redisService;

  @Value("${kplat.token_expire_time:1800}")
  private long tokenExpireTime;

  public TokenServiceImp(TenantMapper tenantMapper, JWTService jwtService,
      TenantUserService tenantUserService, UserContextHandler userContextHandler,RedisService redisService) {
    super(jwtService);
    this.tenantMapper = tenantMapper;
    this.tenantUserService = tenantUserService;
    this.userContextHandler = userContextHandler;
    this.redisService = redisService;
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
    token.setUserId(tokenUserDetails.getId());
    token.setUserType(tokenUserDetails.getType());
    token.setUserCode(tokenUserDetails.getUsername());
    token.setUserName(tokenUserDetails.getUsername());
    token.setTenantId(tokenUserDetails.getTenantId());
    token.setEffectiveTime(tokenExpireTime);
    checkTenant(token);

    String tokenStr = jwtService.create(token);
    log.info("createToken user={} token={}", tokenUserDetails.getUsername(), tokenStr);
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
    TokenUserDetails tokenUserDetails = this.verify(loginToken);
    Token token = tokenUserDetails.getToken();
    token.setTenantId(tenantId);
    checkTenant(token);
    return jwtService.create(token);
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
    Set<String> pemCodes = redisService.get(RedisKeys.USER_PERMS.key(verifyToken.getUserId()));

    return new TokenUserDetails(verifyToken, pemCodes);
  }


  /**
   * @param token token
   * @return void
   * @title checkTenant
   * @description checkTenant
   * @author BiJi'an
   * @date 2023-10-02 00:05
   */
  private void checkTenant(Token token) {
    String tenantId = token.getTenantId();
    if (StringUtils.isEmpty(tenantId)) {
      return;
    }

    LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.eq(Tenant::getId, tenantId);
    queryWrapper.eq(Tenant::getSysDeleteFlag, false);
    Tenant tenant = this.tenantMapper.selectOne(queryWrapper);
    if (tenant == null) {
      throw new AuthException("tenant no exist");
    }

    TenantUser tenantUser = tenantUserService.findByTenantAndUser(tenantId, token.getUserId());
    if (tenantUser != null) {
      token.setUserType(tenantUser.getType());
    } else {

      UserType userType = EnumUtils.fromCode(UserType.class, token.getUserType());
      if (userType == UserType.SUPER_ADMIN) {
        TenantUserResp tenantUserResp = addTenantUser(token, tenant);
        token.setUserType(tenantUserResp.getType());
      } else {
        throw new AuthException("user=" + token.getUserId() + " not in tenant=" + tenant.getId());
      }
    }
  }

  /**
   * @param token  token
   * @param tenant tenant
   * @return io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserResp
   * @title addTenantUser
   * @description addTenantUser
   * @author BiJi'an
   * @date 2023-10-06 01:13
   */
  private TenantUserResp addTenantUser(Token token, Tenant tenant) {
    try {
      userContextHandler.create(token);

      TenantUserReqCreate tenantUserReqCreate = new TenantUserReqCreate();
      tenantUserReqCreate.setUserId(token.getUserId());
      tenantUserReqCreate.setStatus(0);
      tenantUserReqCreate.setType(UserType.TENANT_ADMIN.getCode());
      TenantUserResp save = tenantUserService.save(tenantUserReqCreate);
      log.info("create tenant admin tenantId={},user={}", tenant.getCode(), token.getUserCode());
      return save;
    } finally {
      userContextHandler.remove();
    }


  }
}
