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

import io.github.kylinhunter.commons.init.KCommons;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:51
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Order(value = 3)
public class CoreApplicationRunner implements ApplicationRunner {

  private final SystemDataInitializer systemDataInitializer;

  @Override
  public void run(ApplicationArguments args) {
    KCommons.custom().cleanPackage().scanPackage("io.github.kylinhunter").init();
    systemDataInitializer.init(false);
  }
}
