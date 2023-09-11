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

import io.github.kylinhunter.plat.web.trace.CookieInfo;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author BiJi'an
 * @date 2022-01-30 11:07
 */
public interface TraceExplain {

  TraceExplain put(String key, Object value);

  void recordTimeStart(String key);

  void recordTimeEnd(String key);

  void addTimeCost(String key, long cost);

  long getTimeCost(String key);

  Map<String, Long> getCosts();

  List<CookieInfo> getCookieInfos();

  Map<String, List<String>> getHeaders();

  Map<String, Object> getOthers();

  boolean isDummy();
}
