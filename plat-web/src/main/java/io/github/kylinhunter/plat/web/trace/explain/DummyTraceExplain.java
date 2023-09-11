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
package io.github.kylinhunter.plat.web.trace.explain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.kylinhunter.plat.web.trace.CookieInfo;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author BiJi'an
 * @description 无意义的explain作为默认explain使用
 * @date 2022/01/01
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DummyTraceExplain implements TraceExplain {

  @Override
  public TraceExplain put(String key, Object value) {
    return this;
  }

  @Override
  public void recordTimeStart(String key) {}

  @Override
  public void recordTimeEnd(String key) {}

  @Override
  public void addTimeCost(String key, long cost) {}

  @Override
  public long getTimeCost(String key) {
    return 0;
  }

  @Override
  public Map<String, Long> getCosts() {
    return null;
  }

  @Override
  public List<CookieInfo> getCookieInfos() {
    return null;
  }

  @Override
  public Map<String, List<String>> getHeaders() {
    return null;
  }

  @Override
  public Map<String, Object> getOthers() {
    return null;
  }

  @Override
  public boolean isDummy() {
    return true;
  }
}
