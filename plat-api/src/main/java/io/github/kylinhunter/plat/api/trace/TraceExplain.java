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
package io.github.kylinhunter.plat.api.trace;

import java.util.List;
import java.util.Map;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-30 11:07
 */
public interface TraceExplain {

  TraceExplain put(String key, Object value);

  void costStart(String key);

  void costEnd(String key);

  void addCost(String key, long cost);

  long getCost(String key);

  Map<String, Long> getCosts();

  List<CookieInfo> getCookies();

  void setCookies(List<CookieInfo> cookieInfos);

  Map<String, List<String>> getHeaders();

  void setHeaders(Map<String, List<String>> headers);

  Map<String, Object> getOthers();

}
