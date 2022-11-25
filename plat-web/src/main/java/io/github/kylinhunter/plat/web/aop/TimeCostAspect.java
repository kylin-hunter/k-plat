package io.github.kylinhunter.plat.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.web.config.AppConfig;
import io.github.kylinhunter.plat.web.trace.TraceHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * log time cost
 *
 * @author bijian
 * <p>
 * <p>
 * Mar 16, 2021 11:34:19 AM
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class TimeCostAspect {
    public final AppConfig appConfig;
    private final TraceHandler traceHandler;

    @Pointcut("execution(* com.kylinhunter.plat..*Controller.*(..))")
    private void logController() {
    }

    @Pointcut("execution(* com.kylinhunter.plat..service..*Service*.*(..))")
    private void logService() {
    }

    @Pointcut("@annotation(io.github.kylinhunter.plat.web.aop.TimeWatch)")
    private void logWatch() {
    }

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