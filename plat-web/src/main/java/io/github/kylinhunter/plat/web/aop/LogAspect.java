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
package io.github.kylinhunter.plat.web.aop;

import io.github.kylinhunter.plat.web.trace.TraceHolder;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

@Aspect
@Slf4j
@Order(Integer.MAX_VALUE)
public class LogAspect extends HandlerInterceptorAdapter {

  @Autowired private TraceHolder traceHolder;

  private UrlPathHelper urlPathHelper = new UrlPathHelper();

  private PathMatcher pathMatcher = new AntPathMatcher();

  private static final String LOOKUP_PATH = HandlerMapping.class.getName() + ".lookupPath";

  //    @Autowired
  //    private OpLogSender opLogSender;
  //
  //    private static final String[] FE_PATTERN = PathPatterns.include(PathPatterns.LOG_API_V1);
  //
  //    private static final String[] API_PATTERN = PathPatterns.include(PathPatterns.LOG_OPEN_API);

  @AfterReturning(
      returning = "object",
      pointcut = "execution(* io.github.kylinhunter.plat..*Controller.*(..))")
  public void doController(JoinPoint point, Object object) throws Throwable {
    try {
      ServletRequestAttributes attributes =
          (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      if (attributes != null) {
        HttpServletRequest request = attributes.getRequest();

        //                String lookupPath = this.urlPathHelper.getLookupPathForRequest(request,
        // LOOKUP_PATH);
        //                RefObj<DataFromEnum> refObj = RefObj.of(null);
        //                if (matches(lookupPath, refObj)) {
        //
        //                    // 获取相关请求信息
        //                    Object[] args = point.getArgs();
        //                    CommonReqVO reqVO =
        //                            (CommonReqVO) Stream.of(args).filter(arg -> arg instanceof
        // CommonReqVO)
        //                            .findFirst()
        //                                    .orElse(null);
        //                    if (null == reqVO) {
        //                        return;
        //                    }
        //                    String traceId = traceHandler.get().getId();
        //                    LogEntity entity = new LogEntity();
        //                    entity.setFromEnum(refObj.getData());
        //                    entity.setTraceId(traceId);
        //                    entity.setUrl(lookupPath);
        //                    entity.setReqVO(reqVO);
        //                    if (object instanceof Response) {
        //                        Response resp = (Response) object;
        //                        entity.setRespVO(resp.getData());
        //                    } else {
        //                        entity.setRespVO(object);
        //                    }
        //                    entity.setSysCreated(LocalDateTime.now());
        //                    String entityStr = JsonUtils.toString(entity, false);
        //
        //                    log.info("发送到kafka的日志,traceId:{},log:{}", traceId, entityStr);
        //                    // 发送到kafka
        //                    opLogSender.send(entityStr);

        //                }
      }
    } catch (Exception e) {
      log.error("logAspect  ,error:{}", e);
    }
  }

  //    private boolean matches(String lookupPath, RefObj<DataFromEnum> refObj) {
  //
  //        for (String pattern : FE_PATTERN) {
  //            if (pathMatcher.match(pattern, lookupPath)) {
  //                refObj.setData(DataFromEnum.fe);
  //                return true;
  //            }
  //        }
  //
  //        for (String pattern : API_PATTERN) {
  //            if (pathMatcher.match(pattern, lookupPath)) {
  //                refObj.setData(DataFromEnum.api);
  //                return true;
  //            }
  //        }
  //
  //        return false;
  //    }

}
