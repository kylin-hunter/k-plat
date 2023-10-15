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
package io.github.kylinhunter.plat.web.trace;

import io.github.kylinhunter.plat.api.trace.Trace;
import io.github.kylinhunter.plat.api.trace.TraceExplain;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.api.web.request.RequestConst;
import io.github.kylinhunter.plat.web.config.AppConfig;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.log.LogHelper;
import io.github.kylinhunter.plat.web.request.RequestUtils;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author BiJi'an
 * @description trace
 * @date 2022/01/01
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultTraceHolder implements TraceHolder {

  private final AppConfig appConfig;
  private final ThreadLocal<Trace> traces = InheritableThreadLocal.withInitial(() -> null);

  @Override
  public Trace create(HttpServletRequest request) {
    Trace trace = this.tryCreateTraceFromRequest(request);
    traces.set(trace);
    LogHelper.setTraceId(trace.getId());
    return trace;
  }

  @Override
  public Trace create() {
    Trace trace = new DefaultTrace();
    traces.set(trace);
    return trace;
  }

  @Override
  public Trace get() {

    Trace trace = traces.get();
    if (trace == null) {
      throw new AuthException("no trace found");
    }
    return trace;
  }

  @Override
  public void remove() {
    LogHelper.clearContext();
    traces.set(null);
  }

  /**
   * @return io.github.kylinhunter.plat.commons.trace.Trace
   * @title 从request获取Trace信息
   * @description
   * @author BiJi'an
   * @date 2022/01/01 2:45 下午
   */
  private Trace tryCreateTraceFromRequest(HttpServletRequest request) {
    String traceId = request.getHeader(RequestConst.HEADER_TRACE_ID);
    String token = getToken(request);
    Trace trace = new DefaultTrace(traceId, token);
    TraceExplain explain = trace.getExplain();
    if (appConfig.isDebugEnabled() && RequestUtils.isDebug(request)) {
      trace.setDebug(true);
      explain.setHeaders(RequestUtils.getHeaders(request));
      explain.setCookies(RequestUtils.getCookies(request));
    }
    return trace;
  }

  /**
   * @return java.lang.String
   * @title getTenantId
   * @description
   * @author BiJi'an
   * @date 2021/8/1 3:46 上午
   */
  public String getToken(HttpServletRequest request) {
    // 获取请求头信息authorization信息
    final String authHeader = request.getHeader(RequestConst.HEADER_AUTH);
    log.info("## authHeader= {}", authHeader);
    if (!StringUtils.isBlank(authHeader) && authHeader.startsWith(RequestConst.BEARER)) {
      String token = authHeader.substring(7);
      if (!StringUtils.isBlank(token)) {
        return token;
      }
    }
    return StringUtils.defaultString(request.getParameter(RequestConst.PARAM_TOKEN));
  }
}
