package com.kylinhunter.plat.web.aop;

import java.util.stream.Stream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.auth.context.UserContextHandler;
import com.kylinhunter.plat.api.bean.vo.request.Req;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
//@ConditionalOnExpression("'${a}' == 'a' || '${b}' == 'b'")
@RequiredArgsConstructor
public class ControllerAspect {
    private final UserContextHandler userContextHandler;

    @Around("execution(* com.kylinhunter.plat..*Controller.*(..))")
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