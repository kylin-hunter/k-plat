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
package io.github.kylinhunter.plat.data.configuration.redis;

import lombok.Setter;

/**
 * @description:
 * @author: BiJi'an
 * @create: 2022-03-15 16:16
 */
public enum RedisKeys {
  AGENT_SEARCH_KEYWORD("search.keyword::"),
  AGENT_SEARCH_KEYWORD_TMP("search.keyword_tmp"),
  LICENSE_FLOW_CONTROLLER("flow.controller::");

  private String prefix;
  @Setter private String namespace = "com.kplat::";

  RedisKeys(String prefix) {
    this.prefix = prefix;
  }

  public String key(String name) {
    return namespace + prefix + name;
  }

  public String key() {
    return namespace + prefix;
  }

  public String prefix() {
    return namespace + prefix;
  }
}
