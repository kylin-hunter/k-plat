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
package io.github.kylinhunter.plat.api.middleware.redis;

import org.apache.commons.lang3.StringUtils;

/**
 * @description:
 * @author: BiJi'an
 * @create: 2022-03-15 16:16
 */
public class RedisKey {

  private String prefix;
  private static String NAMESPACE = "com.kplat::";

  public RedisKey(String prefix) {
    this.prefix = prefix;
  }

  public String key(String name) {
    return NAMESPACE + prefix + name;
  }

  public String key() {
    return key(StringUtils.EMPTY);
  }

  public static void setNamespace(String namespace) {
    if (namespace != null && namespace.length() > 0) {
      RedisKey.NAMESPACE = namespace;
    }
  }

  public static RedisKey withPrefix(String prefix) {
    return new RedisKey(prefix);
  }
}
