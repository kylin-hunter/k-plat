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

import io.github.kylinhunter.plat.api.Env;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @description: 主要配置
 * @author: BiJi'an
 * @create: 2021/7/14
 */
@Data
@Slf4j
@ToString
@RefreshScope
@ConfigurationProperties(prefix = "kplat")
public class KplatConfig {

  @Value("${spring.application.name}")
  private String appName;

  @Value("${server.port}")
  private String serverPort;

  private String devVersion;

  private String productVersion;

  private int logWatchThreshold;

  private boolean initialize;


  private boolean debugEnabled;

  private Env env = Env.RELEASE;

  private long tokenExpireTime = 1800;

  @PostConstruct
  private void init() {
    log.info("init config ok {}", this.toString());
  }
}
