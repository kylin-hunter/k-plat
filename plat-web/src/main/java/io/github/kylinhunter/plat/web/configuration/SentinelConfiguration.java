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
import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.web.aop.ControllerAspect;
import io.github.kylinhunter.plat.web.aop.LogAspect;
import io.github.kylinhunter.plat.web.aop.TimeCostAspect;
import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.config.AppConfig;
import io.github.kylinhunter.plat.web.context.DefaultUserContextHandler;
import io.github.kylinhunter.plat.web.i18n.I18nUtils;
import io.github.kylinhunter.plat.web.i18n.KplatLocaleResolver;
import io.github.kylinhunter.plat.web.interceptor.TenantHandlerInterceptor;
import io.github.kylinhunter.plat.web.interceptor.TokenHandlerInterceptor;
import io.github.kylinhunter.plat.web.interceptor.TraceHandlerInterceptor;
import io.github.kylinhunter.plat.web.request.RequestContext;
import io.github.kylinhunter.plat.web.response.ResponseService;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import io.github.kylinhunter.plat.web.sentinel.WebBlockExceptionHandler;
import io.github.kylinhunter.plat.web.trace.DefaultTraceHandler;
import io.github.kylinhunter.plat.web.trace.TraceHandler;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 20:55
 */
@Configuration
public class SentinelConfiguration {
  @Bean
  public WebBlockExceptionHandler webBlockExceptionHandler( ) {
    return new WebBlockExceptionHandler();
  }
}
