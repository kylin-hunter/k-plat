package io.github.kylinhunter.plat.web.interceptor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
public class TraceHandlerInterceptor implements HandlerInterceptor {
    private final TraceHandler traceHandler;

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                             @Nonnull Object handler) {
        traceHandler.create();

        return true;
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                                @Nonnull Object handler, Exception ex) {
        traceHandler.remove();
    }

}
