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
package io.github.kylinhunter.plat.api.trace.explain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Maps;
import io.github.kylinhunter.plat.api.trace.CookieInfo;
import io.github.kylinhunter.plat.api.trace.TraceExplain;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022/01/01
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Slf4j
public class DefaultTraceExplain implements TraceExplain {
  private Map<String, List<String>> headers;
  private List<CookieInfo> cookies;

  private Map<String, Long> costs = Maps.newLinkedHashMap();
  private Map<String, Object> others = Maps.newLinkedHashMap();

  @Override
  public TraceExplain put(String key, Object value) {
    others.put(key, value);
    return this;
  }

  @Override
  public void costStart(String key) {
    costs.put(key, System.currentTimeMillis());
  }

  @Override
  public void costEnd(String key) {
    Long startTime = costs.get(key);
    if (startTime != null && startTime > 0) {
      costs.put(key, System.currentTimeMillis() - startTime);
    } else {
      log.warn("invalid startTime {},{}:", key, startTime);
      costs.put(key, 0L);
    }
  }

  @Override
  public void addCost(String key, long cost) {
    costs.put(key, cost);
  }

  @Override
  public long getCost(String key) {
    Long cost = costs.get(key);
    if (cost != null && cost > 0) {
      return cost;
    } else {
      return 0;
    }
  }
}
