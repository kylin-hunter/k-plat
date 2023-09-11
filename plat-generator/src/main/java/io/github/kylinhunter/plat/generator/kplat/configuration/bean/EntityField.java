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
package io.github.kylinhunter.plat.generator.kplat.configuration.bean;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:53
 */
@Data
public class EntityField {
  private String name; /*field名字*/
  private String className; /*包名+类名*/
  private String classSimpleName; /*类名*/
  private String comment = "";
  private boolean primitive;
  private boolean datetime;
  private boolean supportCreate = true;
  private boolean supportUpdate = true;
  private boolean supportQuery = false;
  private boolean supportResponse = true;
  private String readMethod;
  private String writeMethod;
}
