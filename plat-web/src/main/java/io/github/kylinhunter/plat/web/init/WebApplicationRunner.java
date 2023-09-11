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
package io.github.kylinhunter.plat.web.init;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:51
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Order(value = 1)
public class WebApplicationRunner implements ApplicationRunner {

  private final ConfigurableEnvironment springEnv;

  @Override
  public void run(ApplicationArguments args) {

    MutablePropertySources propSrcs = springEnv.getPropertySources();
    // 获取所有配置
    Map<String, String> props =
        StreamSupport.stream(propSrcs.spliterator(), false)
            .filter(ps -> ps instanceof EnumerablePropertySource)
            .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toMap(Function.identity(), springEnv::getProperty));

    // key 和 value 之间的最小间隙
    int interval = 20;
    int max =
        props.keySet().stream().max(Comparator.comparingInt(String::length)).orElse("").length();

    // 打印
    props.keySet().stream()
        .sorted()
        .forEach(
            k -> {
              int i = max - k.length() + interval;
              String join = String.join("", Collections.nCopies(i, " "));
              System.out.println(String.format("%s%s%s", k, join, props.get(k)));
            });
  }
}
