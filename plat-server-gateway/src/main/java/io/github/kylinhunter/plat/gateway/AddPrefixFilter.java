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
package io.github.kylinhunter.plat.gateway;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-11-30 01:03
 */
@Component
@Slf4j
public class AddPrefixFilter extends AbstractGatewayFilterFactory<AddPrefixFilter.Config> {
  public AddPrefixFilter() {
    // 指定可接收配置数据的类
    super(AddPrefixFilter.Config.class);
  }

  public List<String> shortcutFieldOrder() {
    // 返回可配置的字段
    return Collections.singletonList("prefix");
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      ServerHttpRequest req = exchange.getRequest();

      ServerHttpRequest request =
          req.mutate().header("X-Trace-ID", "bijian" + UUID.randomUUID().toString()).build();
      return chain.filter(exchange.mutate().request(request).build());
    };
  }

  @Override
  public String name() {
    // 返回用于配置的名称
    return "AddPrefix";
  }

  @Getter
  @Setter
  public static class Config {
    // 用于接收可配置字段的值
    private String prefix;
  }
}
