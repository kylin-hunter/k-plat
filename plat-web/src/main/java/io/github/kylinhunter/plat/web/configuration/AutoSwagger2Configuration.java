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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 20:55
 */
@Configuration
@EnableOpenApi
public class AutoSwagger2Configuration {

  /**
   * 配置基本信息
   *
   * @return
   */
  @Bean
  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("plat-server")
        .description("plat-server api")
        .termsOfServiceUrl("https://github.com/kylin-hunter")
        .contact(new Contact("bijian", "https://github.com/kylin-hunter", "kylinhunter@163.com"))
        .version("1.0")
        .build();
  }

  /**
   * 配置文档生成最佳实践
   *
   * @param apiInfo
   * @return
   */
  @Bean
  public Docket createRestApi(ApiInfo apiInfo) {
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(apiInfo)
        .groupName("k-plat")
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build();
  }


  /**
   * @param webEndpointsSupplier        webEndpointsSupplier
   * @param servletEndpointsSupplier    servletEndpointsSupplier
   * @param controllerEndpointsSupplier controllerEndpointsSupplier
   * @param endpointMediaTypes          endpointMediaTypes
   * @param corsProperties              corsProperties
   * @param webEndpointProperties       webEndpointProperties
   * @param environment                 environment
   * @return org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping
   * @throws
   * @title webEndpointServletHandlerMapping
   * @description webEndpointServletHandlerMapping
   * <p>
   * Spring Boot 2.6.x \Swagger 3.0.0 compatibility ,核心问题在 RequestMappingInfo的709行
   * </p>
   * @author BiJi'an
   * @date 2023-10-15 01:15
   */
  @Bean
  public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
      WebEndpointsSupplier webEndpointsSupplier,
      ServletEndpointsSupplier servletEndpointsSupplier,
      ControllerEndpointsSupplier controllerEndpointsSupplier,
      EndpointMediaTypes endpointMediaTypes,
      CorsEndpointProperties corsProperties,
      WebEndpointProperties webEndpointProperties,
      Environment environment) {
    List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
    Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
    allEndpoints.addAll(webEndpoints);
    allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
    allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
    String basePath = webEndpointProperties.getBasePath();
    EndpointMapping endpointMapping = new EndpointMapping(basePath);
    boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties,
        environment, basePath);
    return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes,
        corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath),
        shouldRegisterLinksMapping, null);
  }

  private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties,
      Environment environment, String basePath) {
    return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath)
        || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
  }
}
