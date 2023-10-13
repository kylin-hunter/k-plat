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
package io.github.kylinhunter.plat.core;

import com.google.common.base.Charsets;
import io.github.kylinhunter.plat.web.config.AppConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.SpringVersion;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * @description: 启动logo
 * @author: BiJi'an
 * @create: 2021/7/14
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class WelcomeLogoApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

  private static final AtomicBoolean ATOMIC_BOOLEAN = new AtomicBoolean(false);

  private final AppConfig appConfig;

  @Override
  public void onApplicationEvent(@Nonnull ApplicationReadyEvent event) {
    if (ATOMIC_BOOLEAN.compareAndSet(false, true)) {
      String bannerText = null;
      try {
        bannerText = buildBannerText();
      } catch (Exception e) {
        log.error("build banner text error ", e);
      }

      if (log.isInfoEnabled()) {
        log.info(bannerText);
      } else {
        System.out.print(bannerText);
      }
    }
  }

  String buildBannerText() throws IOException {
    try (InputStream inputStream = new ClassPathResource("/app-banner.txt").getInputStream()) {
      StringBuilder bannerTextBuilder = new StringBuilder();
      bannerTextBuilder.append(SystemUtils.LINE_SEPARATOR).append(SystemUtils.LINE_SEPARATOR);

      List<String> lines = IOUtils.readLines(inputStream, Charsets.UTF_8);
      lines.forEach(line -> bannerTextBuilder.append(line).append(SystemUtils.LINE_SEPARATOR));
      bannerTextBuilder
          .append(SystemUtils.LINE_SEPARATOR)
          .append(" :: Spring Version::")
          .append("( ")
          .append(SpringVersion.getVersion())
          .append(" )")
          .append(SystemUtils.LINE_SEPARATOR)
          .append(" :: Spring Boot Version::")
          .append("( ")
          .append(SpringBootVersion.getVersion())
          .append(" )")
          .append(SystemUtils.LINE_SEPARATOR)
          .append(" :: APP Dev Version::")
          .append("( ")
          .append(appConfig.getDevVersion())
          .append(" )")
          .append(SystemUtils.LINE_SEPARATOR)
          .append(" :: APP Product Version::")
          .append("( ")
          .append(appConfig.getProductVersion())
          .append(" )")
          .append(SystemUtils.LINE_SEPARATOR)
          .append(SystemUtils.LINE_SEPARATOR);

      return bannerTextBuilder.toString();
    }
  }
}
