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
package io.github.kylinhunter.plat.gateway.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author BiJi'an
 * @description
 * @date 2022-11-30 01:59
 */
@Component
public class DCRoutePredicate extends AbstractRoutePredicateFactory<DCRoutePredicate.Config> {
  public DCRoutePredicate() {
    super(Config.class);
  }

  @Override
  public Predicate<ServerWebExchange> apply(Config config) {
    return new Predicate<ServerWebExchange>() {
      @Override
      public boolean test(ServerWebExchange serverWebExchange) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String dcId = headers.getFirst("X-DC-ID");
        if (config.dcid.equals(dcId)) {
          return true;
        }
        return false;
      }
    };
  }

  @Override
  public String name() {
    return "DCRouter";
  }

  /**
   * 将签名 key 与 静态类Config中的属性进行绑定 数组里面下标对应配置文件中配置
   *
   * @return
   */
  @Override
  public List<String> shortcutFieldOrder() {
    return Arrays.asList("dcid");
  }

  @Getter
  @Setter
  public static class Config {
    private String dcid;
  }
}
