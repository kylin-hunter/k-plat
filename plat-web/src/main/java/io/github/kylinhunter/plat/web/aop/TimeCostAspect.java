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

import io.github.kylinhunter.plat.web.config.AppConfig;
import io.github.kylinhunter.plat.web.trace.TraceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * log time cost
 *
 * @author bijian
 *     <p>
 *     <p>Mar 16, 2021 11:34:19 AM
 */
@Aspect
@Slf4j
@RequiredArgsConstructor
public class TimeCostAspect {
  public final AppConfig appConfig;
  private final TraceHandler traceHandler;

  @Pointcut("execution(* io.github.kylinhunter.plat..*Controller.*(..))")
  private void logController() {}

  @Pointcut("execution(* io.github.kylinhunter.plat..service..*Service*.*(..))")
  private void logService() {}

  @Pointcut("@annotation(io.github.kylinhunter.plat.web.aop.TimeWatch)")
  private void logWatch() {}

  @Around("logController() || logService() || logWatch()")
  public Object calTimeCost(ProceedingJoinPoint pjp) throws Throwable {
    String className = pjp.getTarget().getClass().getName();
    String methodName = pjp.getSignature().getName();
    String timerKey = className + "." + methodName;
    long startTime = System.currentTimeMillis();
    Object obj = pjp.proceed();
    long cost = System.currentTimeMillis() - startTime;
    if (cost > appConfig.getWatchThreshold()) {
      log.info("process {}.{} with cost:{}ms", className, methodName, cost);
    }
    traceHandler.get().getTraceExplain().addTimeCost(timerKey, cost);
    return obj;
  }
}
