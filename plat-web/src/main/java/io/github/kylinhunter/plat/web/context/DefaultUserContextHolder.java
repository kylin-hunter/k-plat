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
package io.github.kylinhunter.plat.web.context;

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.auth.context.UserContextHolder;
import io.github.kylinhunter.plat.api.context.DefaultUserContext;
import io.github.kylinhunter.plat.api.context.DummyUserContext;
import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.web.exception.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description trace
 * @date 2022/01/01
 */

@Slf4j
@RequiredArgsConstructor
public class DefaultUserContextHolder implements UserContextHolder {

  private static final UserContext DUMMY_USER_CONTEXT = new DummyUserContext();

  private final ThreadLocal<UserContext> userContexts =
      InheritableThreadLocal.withInitial(() -> DUMMY_USER_CONTEXT);

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
//      if (userContext == null || userContext.isDummy()) {
//        throw new AuthException(" no user content");
//      }
    }
    return userContext;
  }

  @Override
  public void remove() {
    userContexts.set(DUMMY_USER_CONTEXT);
  }
}
