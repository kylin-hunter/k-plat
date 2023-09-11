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
package io.github.kylinhunter.plat.web.response;

import io.github.kylinhunter.commons.date.DateUtils;
import io.github.kylinhunter.commons.json.JsonOptions;
import io.github.kylinhunter.commons.json.JsonUtils;
import io.github.kylinhunter.commons.sys.KConst;
import io.github.kylinhunter.plat.web.request.RequestContext;
import io.github.kylinhunter.plat.web.trace.Trace;
import io.github.kylinhunter.plat.web.trace.TraceHandler;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
  private final TraceHandler traceHandler;
  private final RequestContext requestContext;

  @Override
  public boolean supports(
      MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
    Class<?> dc = methodParameter.getDeclaringClass();
    if (dc.getPackage().getName().startsWith(KConst.K_BASE_PACKAGE)) {
      if (dc.getAnnotation(RestController.class) != null
          || dc.getAnnotation(ResponseBody.class) != null
          || methodParameter.hasMethodAnnotation(ResponseBody.class)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Object beforeBodyWrite(
      Object returnValue,
      MethodParameter methodParameter,
      MediaType mediaType,
      Class<? extends HttpMessageConverter<?>> aClass,
      ServerHttpRequest serverHttpRequest,
      ServerHttpResponse serverHttpResponse) {
    if (returnValue instanceof CustomResponse) {
      return returnValue;
    }
    HttpServletRequest req =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    Trace trace = traceHandler.get();
    trace.setEndTime(System.currentTimeMillis());

    boolean isResponse = returnValue instanceof Response;
    Response<Object> response = isResponse ? (Response<Object>) returnValue : new DefaultResponse();
    response.setTime(trace.getStartTime());
    response.setDurationTime(trace.getDurationTime());
    response.setTraceId(trace.getId());

    if (requestContext.isDebugMode()) { // 更好地调试信息
      response.setStartTime(DateUtils.format(DateUtils.toLocalDateTime(trace.getStartTime())));
      response.setEndTime(DateUtils.format(DateUtils.toLocalDateTime(trace.getEndTime())));
    }
    if (!trace.getTraceExplain().isDummy()) {
      response.setTraceExplain(trace.getTraceExplain());
    }
    log.info(
        req.getRequestURI()
            + "'s response:"
            + JsonUtils.writeToString(response, JsonOptions.NO_FAIL));

    if (!isResponse) {

      response.setData(returnValue);
      if (returnValue instanceof String) {
        return JsonUtils.writeToString(response, JsonOptions.NO_FAIL);
      }
    }
    return response;
  }
}
