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
package io.github.kylinhunter.plat.generator.kplat.configuration;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.github.kylinhunter.plat.generator.kplat.convertor.DefaultFieldConvert;
import io.github.kylinhunter.plat.generator.kplat.convertor.FieldConvert;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BiJi'an
 * @description 策略配置项
 * @date 2021/8/4
 */
@Data
@Accessors(chain = true)
public class StrategyConfigs {
  private Map<Template, StrategyConfig> strategies = Maps.newHashMap();
  private List<Class<?>> entityClasses = Lists.newArrayList();
  private Map<Class<?>, Class<?>> mapperClasses = Maps.newHashMap();
  private FieldConvert fieldConvert = new DefaultFieldConvert();

  public StrategyConfigs(GlobalConfig globalConfig, PackageConfig packageConfig) {
    Arrays.stream(Template.values())
        .forEach(t -> setStrategyConfig(t, new StrategyConfig(t, globalConfig, packageConfig)));
  }

  /**
   * @param entityClass entityClass
   * @return void
   * @title 添加entity
   * @description
   * @author BiJi'an
   * @date 2021/8/4 4:47 下午
   */
  @SuppressWarnings("unused")
  public void addEntityClass(Class<?> entityClass) {
    this.entityClasses.add(entityClass);
  }

  public StrategyConfig get(Template template) {
    return strategies.get(template);
  }

  @SuppressWarnings("UnusedReturnValue")
  public StrategyConfigs setStrategyConfig(Template template, StrategyConfig strategyConfig) {
    strategies.put(template, strategyConfig);
    return this;
  }
}
