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
package io.github.kylinhunter.plat.api.trace;

import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.plat.api.auth.context.DefaultUserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description trace
 * @date 2022/01/01
 */
@Slf4j
public abstract class TraceHolder {

  protected static final ThreadLocal<Trace> traces = InheritableThreadLocal.withInitial(() -> null);

  public static Trace create(User user) {
    Trace trace = new DefaultTrace();
    trace.setUserContext(new DefaultUserContext(user));
    traces.set(trace);
    return trace;
  }

  public static Trace get() {

    Trace trace = traces.get();
    if (trace == null) {
      throw new BizException("no trace found");
    }
    return trace;
  }

  public static void remove() {
    traces.set(null);
  }
}
