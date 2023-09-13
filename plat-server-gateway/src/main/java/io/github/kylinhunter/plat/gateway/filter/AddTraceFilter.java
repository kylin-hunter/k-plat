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
package io.github.kylinhunter.plat.gateway.filter;

import io.github.kylinhunter.plat.api.web.request.RequestConst;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-11-30 01:03
 */
@Component
@Slf4j
public class AddTraceFilter extends AbstractGatewayFilterFactory<AddTraceFilter.Config> {

  private static final String TYPE_UUID = "uuid";
  private static final String TYPE_NUMBER = "number";
  private static final AtomicLong idCounter = new AtomicLong(0);

  public AddTraceFilter() {
    super(AddTraceFilter.Config.class);
  }

  public List<String> shortcutFieldOrder() {

    return Collections.singletonList("type");
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      ServerHttpRequest req = exchange.getRequest();

      List<String> traceIds = req.getHeaders().get(RequestConst.HEADER_TRACE_ID);
      if (CollectionUtils.isEmpty(traceIds)) {

        String traceId = "";
        if (TYPE_NUMBER.equalsIgnoreCase(config.type)) {
          traceId = String.valueOf(idCounter.get());
        } else {
          traceId = UUID.randomUUID().toString();
        }

        ServerHttpRequest request = req.mutate().header(RequestConst.HEADER_TRACE_ID, traceId)
            .build();
        return chain.filter(exchange.mutate().request(request).build());
      }
      return chain.filter(exchange);


    };
  }

  @Override
  public String name() {
    // 返回用于配置的名称
    return "AddTrace";
  }

  @Getter
  @Setter
  public static class Config {

    // 用于接收可配置字段的值
    private String type;
  }
}
