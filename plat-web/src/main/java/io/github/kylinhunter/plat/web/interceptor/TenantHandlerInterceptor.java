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
package io.github.kylinhunter.plat.web.interceptor;

import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.trace.TraceHandler;
import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * trace拦截器
 *
 * @author BiJi'an
 * @description
 * @date 2022/01/01
 */

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@Slf4j
public class TenantHandlerInterceptor implements HandlerInterceptor {
  private final TraceHandler traceHandler;
  private final UserContextHandler userContextHandler;

  @Override
  public boolean preHandle(
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull Object handler) {
    UserContext userContext = userContextHandler.get();
    String tenantId = userContext.getTenantId();
    if (StringUtils.isEmpty(tenantId)) {
      throw new AuthException("tenantId is empty");
    }

    return true;
  }

  @Override
  public void afterCompletion(
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull Object handler,
      Exception ex) {
    userContextHandler.remove();
  }
}
