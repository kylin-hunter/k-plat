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
package io.github.kylinhunter.plat.dao.service.local.ex;

import io.github.kylinhunter.commons.collections.MapUtils;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 * @description
 * @author BiJi'an
 * @date 2022-01-22 19:41
 */
public class FilterCustoms {
  public static final Map<String, FilterCustom> DATA = MapUtils.newHashMap();

  @PostConstruct
  private void init() {}

  public FilterCustom getFilterCustom(String field) {
    return DATA.get(field);
  }
}