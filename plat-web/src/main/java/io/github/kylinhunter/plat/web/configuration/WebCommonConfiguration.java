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
import io.github.kylinhunter.plat.web.error.GlobalExceptionHandler;
import io.github.kylinhunter.plat.web.i18n.I18nUtils;
import io.github.kylinhunter.plat.web.i18n.KplatLocaleResolver;
import io.github.kylinhunter.plat.web.init.WebApplicationRunner;
import io.github.kylinhunter.plat.web.interceptor.TenantHandlerInterceptor;
import io.github.kylinhunter.plat.web.interceptor.TokenHandlerInterceptor;
import io.github.kylinhunter.plat.web.interceptor.TraceHandlerInterceptor;
import io.github.kylinhunter.plat.web.request.RequestContext;
import io.github.kylinhunter.plat.web.request.WebDataBinderConfig;
import io.github.kylinhunter.plat.web.response.ResponseAdvice;
import io.github.kylinhunter.plat.web.response.ResponseService;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import io.github.kylinhunter.plat.web.trace.DefaultTraceHandler;
import io.github.kylinhunter.plat.web.trace.TraceHandler;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
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


  @Bean
  public DefaultUserContextHandler defaultUserContextHandler() {
    return new DefaultUserContextHandler();
  }

  @Bean
  public ControllerAspect controllerAspect(UserContextHandler userContextHandler) {
    return new ControllerAspect(userContextHandler);
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
  public RequestContext requestContext(HttpServletRequest request) {
    return new RequestContext(request);
  }

  @Bean
  public DefaultTraceHandler defaultTraceHandler(RequestContext requestContext) {
    return new DefaultTraceHandler(requestContext);
  }

  @Bean
  public TimeCostAspect timeCostAspect(AppConfig appConfig,
      DefaultTraceHandler defaultTraceHandler) {
    return new TimeCostAspect(appConfig, defaultTraceHandler);
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
  public TokenHandlerInterceptor tokenHandlerInterceptor(TraceHandler traceHandler,
      UserContextHandler userContextHandler, JWTService jwtService) {
    return new TokenHandlerInterceptor(traceHandler, userContextHandler, jwtService);
  }

  @Bean
  public TenantHandlerInterceptor tenantHandlerInterceptor(TraceHandler traceHandler,
      UserContextHandler userContextHandler) {
    return new TenantHandlerInterceptor(traceHandler, userContextHandler);
  }


  @Bean
  public ResponseService responseService(TraceHandler traceHandler, RequestContext requestContext) {
    return new ResponseService(traceHandler, requestContext);
  }

  @Bean
  public ResponseWriter responseWriter(ResponseService responseService) {
    return new ResponseWriter(responseService);
  }

  @Bean
  public TraceHandlerInterceptor traceHandlerInterceptor(TraceHandler traceHandler) {
    return new TraceHandlerInterceptor(traceHandler);
  }

  @Bean
  public WebApplicationRunner webApplicationRunner(
      ConfigurableEnvironment configurableEnvironment) {
    return new WebApplicationRunner(configurableEnvironment);
  }

  ;

  @Bean
  public ResponseAdvice responseAdvice(TraceHandler traceHandler, RequestContext requestContext) {
    return new ResponseAdvice(traceHandler, requestContext);
  }
  @Bean
  public WebDataBinderConfig webDataBinderConfig() {
    return new WebDataBinderConfig();
  }


}
