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
package io.github.kylinhunter.plat.generator.kplat.convertor;

import io.github.kylinhunter.plat.generator.kplat.configuration.StrategyConfig;
import io.github.kylinhunter.plat.generator.kplat.configuration.bean.EntityField;
import java.lang.reflect.Field;

/**
 * @author BiJi'an
 * @description 字段转换
 * @date 2022/01/01
 */
public interface FieldConvert {
  /**
   * @param strategyConfig strategyConfig
   * @param field field
   * @return io.github.kylinhunter.plat.generator.custom.convertor.EntityField
   * @title 类型转换
   * @description
   * @author BiJi'an
   * @date 2022/01/01 11:18 上午
   */
  EntityField convert(StrategyConfig strategyConfig, Class<?> entityClass, Field field);
}
