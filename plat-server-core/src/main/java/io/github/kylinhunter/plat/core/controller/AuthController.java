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

import io.github.kylinhunter.plat.api.auth.ReqLogin;
import io.github.kylinhunter.plat.api.auth.ReqTenantToken;
import io.github.kylinhunter.plat.core.service.local.AuthService;
import io.github.kylinhunter.plat.web.controller.CommonController;
import io.github.kylinhunter.plat.web.response.DefaultResponse;
import io.github.kylinhunter.plat.web.trace.TraceHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class AuthController extends CommonController {

  private final TraceHandler traceHandler;
  private final AuthService authService;

  @PostMapping(value = "/login")
  @ApiOperation("login")
  public DefaultResponse<String> login(@Validated @RequestBody ReqLogin reqLogin) {
    return new DefaultResponse(authService.login(reqLogin));
  }

  @PostMapping(value = "/auth/create_tenant_token")
  @ApiOperation("create_tenant_token")
  public DefaultResponse<String> createTenantToken(
      @Validated @RequestBody ReqTenantToken reqTenantToken) {

    return new DefaultResponse(
        authService.createTenantToken(traceHandler.get().getToken(), reqTenantToken.getTenantId()));
  }

  @PostMapping(value = "/auth/verify_token")
  @ApiOperation("verify_token")
  public DefaultResponse<String> verify() {
    return new DefaultResponse(authService.verify(traceHandler.get().getToken()));
  }
}
