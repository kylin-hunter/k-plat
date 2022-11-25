
package com.kylinhunter.plat.web.context;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.api.context.DefaultUserContext;
import io.github.kylinhunter.plat.api.context.DummyUserContext;
import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.web.exception.AuthException;

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

    private static final UserContext DUMMY_USER_CONTEXT = new DummyUserContext();

    private final ThreadLocal<UserContext> userContexts = InheritableThreadLocal.withInitial(() -> DUMMY_USER_CONTEXT);

    @Override
    public UserContext create(Token token) {
        DefaultUserContext defaultUserContext = new DefaultUserContext(token);
        userContexts.set(defaultUserContext);
        return defaultUserContext;
    }

    @Override
    public UserContext create(User user) {
        DefaultUserContext defaultUserContext = new DefaultUserContext(user);
        userContexts.set(defaultUserContext);
        return defaultUserContext;
    }

    @Override
    public UserContext get() {
        return this.get(true);
    }

    @Override
    public UserContext get(boolean check) {

        final UserContext userContext = userContexts.get();
        if (check) {
            if (userContext == null || userContext.isDummy()) {
                throw new AuthException(" no user content");
            }
        }
        return userContext;
    }

    @Override
    public void remove() {
        userContexts.set(DUMMY_USER_CONTEXT);
    }

}
