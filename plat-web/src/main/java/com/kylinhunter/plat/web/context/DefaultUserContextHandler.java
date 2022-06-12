
package com.kylinhunter.plat.web.context;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.auth.Token;
import com.kylinhunter.plat.api.context.DefaultUserContext;
import com.kylinhunter.plat.api.context.DummyUserContext;
import com.kylinhunter.plat.api.context.UserContext;
import com.kylinhunter.plat.web.auth.JWTService;
import com.kylinhunter.plat.web.trace.Trace;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description trace
 * @date 2022/01/01
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultUserContextHandler implements UserContextHandler {
    private final JWTService jwtService;

    private static final UserContext DUMMY_USER_CONTEXT = new DummyUserContext();

    private final ThreadLocal<UserContext> userContexts = InheritableThreadLocal.withInitial(() -> DUMMY_USER_CONTEXT);

    @Override
    public UserContext create(Trace trace) {
        Token token = jwtService.verify(trace.getToken());
        DefaultUserContext defaultUserContext = new DefaultUserContext(token);
        userContexts.set(defaultUserContext);
        return defaultUserContext;
    }

    @Override
    public UserContext get() {
        return userContexts.get();
    }

    @Override
    public void remove() {
        userContexts.set(DUMMY_USER_CONTEXT);
    }

}
