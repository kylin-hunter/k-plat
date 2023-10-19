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
package io.github.kylinhunter.plat.web.security;

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.web.exception.WebErrInfos;
import io.github.kylinhunter.plat.web.response.DefaultResponse;
import io.github.kylinhunter.plat.web.response.Response;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-15 01:43
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultLogoutSuccessHandler implements LogoutSuccessHandler {

  private final TokenService tokenService;
  private final ResponseWriter responseWriter;
  private static final Response<String> successResp = new DefaultResponse<>("log out seccess");

  @Override
  public void onLogoutSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    try {
      Token token = tokenService.invalidToken();
      if (token != null) {
        log.info("{}/{} log out success", token.getUserId(), token.getUserName());
      }
      responseWriter.writeJson(successResp);
    } catch (Exception e) {
      log.error("logout error", e);
      responseWriter.writeErr(WebErrInfos.AUTH_ERROR, "logout error", "");
    }
  }
}
