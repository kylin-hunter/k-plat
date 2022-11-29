package io.github.kylinhunter.plat.web.interceptor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.trace.Trace;
import io.github.kylinhunter.plat.web.trace.TraceHandler;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * trace拦截器
 *
 * @author BiJi'an
 * @description
 * @date 2022/01/01
 **/
@Component
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@Slf4j
public class TokenHandlerInterceptor implements HandlerInterceptor {
    private final TraceHandler traceHandler;
    private final UserContextHandler userContextHandler;
    private final JWTService jwtService;

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                             @Nonnull Object handler) {
        Trace trace = traceHandler.get();
        Token token = jwtService.verify(trace.getToken());

        userContextHandler.create(token);

        return true;
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                                @Nonnull Object handler, Exception ex) {
        userContextHandler.remove();
    }
}
