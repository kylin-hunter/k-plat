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
import io.github.kylinhunter.plat.api.auth.context.UserContextHolder;
import io.github.kylinhunter.plat.web.aop.ControllerAspect;
import io.github.kylinhunter.plat.web.aop.LogAspect;
import io.github.kylinhunter.plat.web.aop.TimeCostAspect;
import io.github.kylinhunter.plat.web.auth.JWTService;
import io.github.kylinhunter.plat.web.config.AppConfig;
import io.github.kylinhunter.plat.web.context.DefaultUserContextHolder;
import io.github.kylinhunter.plat.web.i18n.I18nUtils;
import io.github.kylinhunter.plat.web.i18n.KplatLocaleResolver;
import io.github.kylinhunter.plat.web.init.WebApplicationRunner;
import io.github.kylinhunter.plat.web.interceptor.TenantHandlerInterceptor;
import io.github.kylinhunter.plat.web.request.WebDataBinderConfig;
import io.github.kylinhunter.plat.web.response.ResponseAdvice;
import io.github.kylinhunter.plat.web.response.ResponseService;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import io.github.kylinhunter.plat.web.trace.DefaultTraceHolder;
import io.github.kylinhunter.plat.web.trace.TraceFilter;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 20:55
 */
@Configuration
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class AutoWebCommonConfiguration {

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


  @Bean
  public DefaultUserContextHolder defaultUserContextHandler() {
    return new DefaultUserContextHolder();
  }

  @Bean
  public ControllerAspect controllerAspect(UserContextHolder userContextHolder) {
    return new ControllerAspect(userContextHolder);
  }

  @Bean
  public LogAspect loggerAspect() {
    return new LogAspect();
  }

  @Bean

  public AppConfig appConfig() {
    return new AppConfig();
  }


  @Bean
  public TraceHolder traceHolder() {
    return new DefaultTraceHolder();
  }

  @Bean
  public TimeCostAspect timeCostAspect(AppConfig appConfig, TraceHolder traceHolder) {
    return new TimeCostAspect(appConfig, traceHolder);
  }

  @Bean
  public I18nUtils i18nUtils(MessageSource messageSource) {
    return new I18nUtils(messageSource);
  }

  @Bean
  public JWTService jwtService() {
    return new JWTService();
  }


  @Bean
  public TenantHandlerInterceptor tenantHandlerInterceptor(TraceHolder traceHolder) {
    return new TenantHandlerInterceptor(traceHolder);
  }


  @Bean
  public ResponseService responseService(TraceHolder traceHolder) {
    return new ResponseService(traceHolder);
  }

  @Bean
  public ResponseWriter responseWriter(ResponseService responseService) {
    return new ResponseWriter(responseService);
  }


  @Bean
  public WebApplicationRunner webApplicationRunner(
      ConfigurableEnvironment configurableEnvironment) {
    return new WebApplicationRunner(configurableEnvironment);
  }

  @Bean
  public TraceFilter traceFilter(TraceHolder traceHolder) {
    return new TraceFilter(traceHolder);
  }


  @Bean
  public ResponseAdvice responseAdvice(TraceHolder traceHolder) {
    return new ResponseAdvice(traceHolder);
  }

  @Bean
  public WebDataBinderConfig webDataBinderConfig() {
    return new WebDataBinderConfig();
  }


}
