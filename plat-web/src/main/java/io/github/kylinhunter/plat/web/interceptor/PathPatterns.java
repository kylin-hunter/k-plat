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
package io.github.kylinhunter.plat.web.interceptor;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

/**
 * @author BiJi'an
 * @description 预定义拦截路径若干
 * @date 2022-01-01 16:59
 */
public class PathPatterns {
  public static final PathPattern ALL = PathPattern.builder().build().add("/**");

  public static final PathPattern LOGIN = PathPattern.builder().build().add("/login");
  public static final PathPattern AUTH = PathPattern.builder().build().add("/auth/**");
  public static final PathPattern API_V1_TENANT =
      PathPattern.builder().build().add("/api/v1/core/tenants/**");
  public static final PathPattern API_V1_USER =
      PathPattern.builder().build().add("/api/v1/core/users/**");
  public static final PathPattern API_V1_ROLES =
      PathPattern.builder().build().add("/api/v1/core/roles/**");
  public static final PathPattern API_V1_STORAGE =
      PathPattern.builder().build().add("/api/v1/storage/**");

  public static final PathPattern API_V1 = PathPattern.builder().build().add("/api/v1/**");

  public static final PathPattern SWAGGER =
      PathPattern.builder()
          .build()
          .add("/swagger-resources/**")
          .add("/webjars/**")
          .add("/v2/**")
          .add("/swagger-ui.html/**");

  public static String[] of(PathPattern... patterns) {
    if (patterns.length <= 0) {
      return new String[0];
    } else if (patterns.length == 1) {
      return patterns[0].paths.toArray(new String[0]);
    } else {
      return Arrays.stream(patterns)
          .map(e -> e.paths.toArray(new String[0]))
          .flatMap(Arrays::stream)
          .distinct()
          .toArray(String[]::new);
    }
  }

  @Data
  @Builder
  public static class PathPattern {
    @Builder.Default private Set<String> paths = Sets.newHashSet();

    public PathPattern add(String path) {
      paths.add(path);
      return this;
    }
  }
}
