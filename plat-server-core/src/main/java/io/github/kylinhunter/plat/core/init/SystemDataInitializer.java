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
package io.github.kylinhunter.plat.core.init;

import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.core.init.initializer.Initializer;
import io.github.kylinhunter.plat.web.config.KplatConfig;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-16 01:25
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SystemDataInitializer {

  private final KplatConfig kplatConfig;
  private final TraceHolder traceHolder;

  private final Map<String, Initializer> initializers;

  public boolean init(boolean force) {
    try {
      traceHolder.create();
      return _init(force);
    } finally {
      traceHolder.remove();
    }
  }

  private boolean _init(boolean force) {

    if (force || kplatConfig.isInitialize()) {
      TreeMap<Integer, Initializer> allIntializers =
          initializers.values().stream()
              .collect(
                  Collectors.toMap(
                      Initializer::order,
                      e -> e,
                      (o, n) -> {
                        throw new IllegalStateException(String.format("Duplicate key %s", o));
                      },
                      TreeMap::new));
      allIntializers
          .values()
          .forEach(
              initializer -> {
                log.info("init order:" + initializer.order());
                initializer.init();
              });
      log.info("init ok");
    } else {
      log.info("skip init");
    }
    return true;
  }
}
