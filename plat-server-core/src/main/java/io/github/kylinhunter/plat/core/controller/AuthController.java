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
package io.github.kylinhunter.plat.core.controller;

import io.github.kylinhunter.plat.api.auth.bean.vo.ReqTenantToken;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.web.controller.CommonController;
import io.github.kylinhunter.plat.web.response.DefaultResponse;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthRoleController 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-01-01
 */
@RestController
@Api(value = "auth相关")
@Data
@EqualsAndHashCode(callSuper = false)
@RequestMapping("/auth")
public class AuthController extends CommonController {

  private final TraceHolder traceHolder;
  private final TokenService tokenService;

  @PostMapping(value = "/tenant_login")
  @ApiOperation("tenant_login")
  public DefaultResponse<String> createTenantToken(
      @Validated @RequestBody ReqTenantToken reqTenantToken) {
    return new DefaultResponse(tokenService.createTenantToken(reqTenantToken));
  }

  @PostMapping(value = "/verify_token")
  @ApiOperation("verify_token")
  public DefaultResponse<String> verify(
      HttpServletRequest request, @RequestParam(name = "token", required = false) String token) {
    if (StringUtils.isEmpty(token)) {
      token = traceHolder.get().getToken();
    }
    return new DefaultResponse(tokenService.verify(token));
  }
}
