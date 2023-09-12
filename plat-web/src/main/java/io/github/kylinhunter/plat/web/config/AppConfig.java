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
package io.github.kylinhunter.plat.web.config;

import io.github.kylinhunter.commons.lang.EnumUtils;
import io.github.kylinhunter.plat.api.Env;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @description: 主要配置
 * @author: BiJi'an
 * @create: 2021/7/14
 */
@Component
@Data
@Slf4j
@ToString
@RefreshScope
public class AppConfig {
  @Value("${spring.application.name}")
  private String appName;

  @Value("${server.port}")
  private String serverPort;

  @Value("${kplat.devVersion:1.0}")
  private String devVersion;

  @Value("${kplat.productVersion:1.0}")
  private String productVersion;

  @Value("${kplat.threshold:0}")
  private int watchThreshold;

  @Value("${kplat.initialize.enabled:false}")
  private boolean initialize;

  @Value("${kplat.env:RELEASE}")
  private String envStr;

  private Env env;

  @PostConstruct
  private void init() {
    this.env = EnumUtils.fromName(Env.class, this.envStr);
    log.info("init config ok {}", this.toString());
  }
}
