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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.github.kylinhunter.commons.date.DatePatterns;
import io.github.kylinhunter.plat.web.i18n.KplatLocaleResolver;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 20:55
 */
@Configuration
public class WebCommonConfiguration {

  @Bean // 将区域信息对象注册到容器
  public LocaleResolver localeResolver() {
    return new KplatLocaleResolver();
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customJackson() {
    return jacksonObjectMapperBuilder -> {
      jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.ALWAYS);
      jacksonObjectMapperBuilder.failOnUnknownProperties(false);
      //
      // jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
      LocalDateTimeSerializer localDateTimeSerializer =
          new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePatterns.DATE_TIME));

      LocalDateTimeDeserializer localDateTimeDeserializer =
          new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePatterns.DATE_TIME));

      jacksonObjectMapperBuilder.serializers(localDateTimeSerializer);
      jacksonObjectMapperBuilder.deserializers(localDateTimeDeserializer);
    };
  }
}
