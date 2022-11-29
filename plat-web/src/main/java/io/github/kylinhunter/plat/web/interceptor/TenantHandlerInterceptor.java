package io.github.kylinhunter.plat.web.interceptor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.web.exception.AuthException;
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
public class TenantHandlerInterceptor implements HandlerInterceptor {
    private final TraceHandler traceHandler;
    private final UserContextHandler userContextHandler;

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                             @Nonnull Object handler) {
        UserContext userContext = userContextHandler.get();
        String tenantId = userContext.getTenantId();
        if (StringUtils.isEmpty(tenantId)) {
            throw new AuthException("tenantId is empty");
        }

        return true;
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                                @Nonnull Object handler, Exception ex) {
        userContextHandler.remove();
    }
}
