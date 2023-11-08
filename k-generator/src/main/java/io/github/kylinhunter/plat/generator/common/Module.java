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
package io.github.kylinhunter.plat.generator.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.github.kylinhunter.commons.classloader.ExClassLoaderUtil;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 13:42
 */
@Data
@Setter
@Accessors(chain = true)
public class Module {
  private String name;
  private String tablePrefix;
  private List<String> tables = Lists.newArrayList();
  private List<String> entityClassNames = Lists.newArrayList();
  private List<Class<?>> entityClasses = Lists.newArrayList();
  private Map<String, String> mapperClassNames = Maps.newHashMap();
  private Map<Class<?>, Class<?>> mapperClasses = Maps.newHashMap();

  public Module(String name) {
    this.name = name;
  }

  public String[] getTableArr() {
    return tables.toArray(new String[0]);
  }

  public Module addTable(String table) {
    if (!this.tables.contains(table)) {
      this.tables.add(table);
    }
    return this;
  }

  public void loadClasses() {
    entityClassNames.forEach(
        e -> {
          entityClasses.add(ExClassLoaderUtil.loadClass(e));

          //            entityClasses.add(ReflectionUtil.loadClass(e));
        });
    mapperClassNames.forEach(
        (k, v) -> {
          mapperClasses.put(ExClassLoaderUtil.loadClass(k), ExClassLoaderUtil.loadClass(v));

          //            mapperClasses.put(ReflectionUtil.loadClass(k), ReflectionUtil.loadClass(v));
        });
  }
}
