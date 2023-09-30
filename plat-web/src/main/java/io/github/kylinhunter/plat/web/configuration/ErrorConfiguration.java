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
package io.github.kylinhunter.plat.web.configuration;

import io.github.kylinhunter.plat.web.error.ErrorMessageController;
import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BiJi'an
 * @description
 * @date 2022-11-17 02:13
 */
@Configuration
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class ErrorConfiguration {

  @Bean
  public ErrorMessageController basicErrorController(
      ErrorAttributes errorAttributes,
      ServerProperties serverProperties,
      ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {

    return new ErrorMessageController(
        errorAttributes, serverProperties.getError(), errorViewResolversProvider.getIfAvailable());
  }
}
