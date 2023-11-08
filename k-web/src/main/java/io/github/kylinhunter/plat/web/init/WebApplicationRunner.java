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
package io.github.kylinhunter.plat.web.init;

import io.github.kylinhunter.commons.exception.info.ErrInfoManager;
import io.github.kylinhunter.commons.init.KCommons;
import io.github.kylinhunter.plat.web.log.LogHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:51
 */
@Slf4j
@RequiredArgsConstructor
@Order(value = 1)
public class WebApplicationRunner implements ApplicationRunner {

  private final ConfigurableEnvironment configurableEnvironment;

  @Override
  public void run(ApplicationArguments args) {

    initKCommons();
    initEnv();
  }

  /**
   * @return void
   * @throws
   * @title initKCommons
   * @description initKCommons
   * @author BiJi'an
   * @date 2023-09-24 15:18
   */
  private void initKCommons() {
    log.info("{} init k-commons start", LogHelper.SYS_TAG);
    KCommons.custom().cleanPackage().scanPackage("io.github.kylinhunter").init();
    ErrInfoManager.println();
    log.info("{} init k-commons end", LogHelper.SYS_TAG);
  }

  private void initEnv() {
    log.info("{} init env start", LogHelper.SYS_TAG);
    EnvHelper.printEnv(configurableEnvironment);
    log.info("{} init env end", LogHelper.SYS_TAG);
  }
}
