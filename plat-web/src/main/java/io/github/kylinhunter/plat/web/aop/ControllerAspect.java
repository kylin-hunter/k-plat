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

import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.api.bean.vo.request.Req;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
// @ConditionalOnExpression("'${a}' == 'a' || '${b}' == 'b'")
@RequiredArgsConstructor
public class ControllerAspect {
  private final UserContextHandler userContextHandler;

  @Around("execution(* io.github.kylinhunter.plat..*Controller.*(..))")
  public Object allController(ProceedingJoinPoint point) throws Throwable {
    this.setTraceMsg(point);
    return point.proceed();
  }

  protected void setTraceMsg(ProceedingJoinPoint point) {
    Object[] args = point.getArgs();
    if (args != null && args.length > 0) {
      Req req = (Req) Stream.of(args).filter(arg -> arg instanceof Req).findFirst().orElse(null);
      if (req != null) {
        //                UserContext userContext = userContextHandler.get();
        //                req.setUserContext(userContext);

      }
    }
  }
}
