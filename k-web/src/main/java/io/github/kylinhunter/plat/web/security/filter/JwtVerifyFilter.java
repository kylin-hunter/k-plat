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
package io.github.kylinhunter.plat.web.security.filter;

import io.github.kylinhunter.commons.exception.common.KRuntimeException;
import io.github.kylinhunter.plat.api.trace.Trace;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import io.github.kylinhunter.plat.web.trace.WebTraceHolder;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 21:11
 */
public class JwtVerifyFilter extends BasicAuthenticationFilter {

  private final TokenService tokenService;
  private final ResponseWriter responseWriter;

  public JwtVerifyFilter(
      AuthenticationManager authenticationManager,
      TokenService tokenService,
      ResponseWriter responseWriter) {
    super(authenticationManager);
    this.tokenService = tokenService;
    this.responseWriter = responseWriter;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    try {

      Trace trace = WebTraceHolder.get();
      String token = trace.getToken();
      TokenUserDetails tokenUserDetails = null;
      try {
        tokenUserDetails = this.tokenService.verify(token);
      } catch (AuthException e) {
        logger.error("verify token error", e);
        responseWriter.write(e, trace.isDebug());
        return;
      }
      trace.setVerifyToken(tokenUserDetails.getVerifyToken());
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(
              tokenUserDetails, null, tokenUserDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

      chain.doFilter(request, response);

    } catch (KRuntimeException e) {
      throw new AuthenticationServiceException("verify token error" + e.getMessage(), e);

    } catch (Exception e) {
      throw new AuthenticationServiceException("verify token error", e);
    }
  }
}
