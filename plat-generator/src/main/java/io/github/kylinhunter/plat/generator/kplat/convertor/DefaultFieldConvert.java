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

import io.github.kylinhunter.commons.branch.BranchExecutor;
import io.github.kylinhunter.commons.branch.BranchExecutors;
import io.github.kylinhunter.plat.generator.kplat.configuration.StrategyConfig;
import io.github.kylinhunter.plat.generator.kplat.configuration.bean.EntityField;
import io.swagger.annotations.ApiModelProperty;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author BiJi'an
 * @description 默认字段转换
 * @date 2022/01/01
 */
public class DefaultFieldConvert implements FieldConvert {

  private static final String SERIAL_VERSIONU_ID = "serialVersionUID";

  @Override
  public EntityField convert(StrategyConfig strategyConfig, Class<?> entityClass, Field field) {

    final BranchExecutor<Field, EntityField> branchExecutor =
        BranchExecutors.use(field, EntityField.class);
    return branchExecutor
        .test(
            branchExecutor
                .predicate(f -> strategyConfig.getSkipFields().contains(f.getName()))
                .then(f -> null))
        .others(f -> processDefault(strategyConfig, entityClass, f));
  }

  /**
   * @param strategyConfig strategyConfig
   * @param field          field
   * @return io.github.kylinhunter.plat.generator.kplat.configuration.bean.EntityField
   * @title 默认的转换
   * @description
   * @author BiJi'an
   * @date 2022/01/01 5:00 下午
   */
  @SuppressWarnings("rawtypes")
  public EntityField processDefault(
      StrategyConfig strategyConfig, Class<?> entityClass, Field field) {
    EntityField entityField = new EntityField();
    if (SERIAL_VERSIONU_ID.equals(field.getName())) {
      return null;
    }
    entityField.setName(field.getName());
    entityField.setClassName(field.getType().getCanonicalName());
    entityField.setClassSimpleName(field.getType().getSimpleName());
    ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
    if (apiModelProperty != null) {
      entityField.setComment(StringUtils.defaultIfBlank(apiModelProperty.value(), field.getName()));
    } else {
      entityField.setComment(field.getName());
    }
    entityField.setPrimitive(field.getType().isPrimitive());

    final Class<?>[] classes = new Class[]{LocalDate.class, LocalDateTime.class, Date.class};
    for (Class clazz : classes) {
      if (field.getType() == clazz) {
        entityField.setDatetime(true);
      }
    }
    PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(entityClass, field.getName());
    if (pd != null) {
      Method readMethod = pd.getReadMethod();
      Method wirteMethod = pd.getWriteMethod();
      entityField.setReadMethod(
          readMethod.getReturnType().getSimpleName() + " " + readMethod.getName() + "()");

      entityField.setWriteMethod(
          "void "
              + wirteMethod.getName()
              + " ("
              + wirteMethod.getParameterTypes()[0].getSimpleName()
              + " "
              + entityField.getName()
              + ")");
    }

    return entityField;
  }
}
