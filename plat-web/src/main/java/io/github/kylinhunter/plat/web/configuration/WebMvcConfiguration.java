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

import io.github.kylinhunter.plat.web.interceptor.PathPatterns;
import io.github.kylinhunter.plat.web.interceptor.TenantHandlerInterceptor;
import io.github.kylinhunter.plat.web.interceptor.TokenHandlerInterceptor;
import io.github.kylinhunter.plat.web.interceptor.TraceHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author BiJi'an
 * @description 拦截器
 * @date 2022/01/01
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
  private final TraceHandlerInterceptor traceHandlerInterceptor;
  private final TokenHandlerInterceptor tokenHandlerInterceptor;
  private final TenantHandlerInterceptor tenantHandlerInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(new SentinelWebInterceptor()).addPathPatterns("/**");

//    registry
//        .addInterceptor(traceHandlerInterceptor)
//        .addPathPatterns(PathPatterns.of(PathPatterns.ALL));

//    registry
//        .addInterceptor(tokenHandlerInterceptor)
//        .addPathPatterns(PathPatterns.of(PathPatterns.API_V1))
//        .addPathPatterns(PathPatterns.of(PathPatterns.AUTH));

    registry
        .addInterceptor(tenantHandlerInterceptor)
        .addPathPatterns(PathPatterns.of(PathPatterns.API_V1))
        .excludePathPatterns(PathPatterns.of(PathPatterns.API_V1_USER))
        .excludePathPatterns(PathPatterns.of(PathPatterns.API_V1_ROLES))
        .excludePathPatterns(PathPatterns.of(PathPatterns.API_V1_TENANT))
        .excludePathPatterns(PathPatterns.of(PathPatterns.API_V1_STORAGE));
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
