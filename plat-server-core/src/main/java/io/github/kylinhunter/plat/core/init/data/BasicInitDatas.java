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
package io.github.kylinhunter.plat.core.init.data;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-15 01:33
 */
@Getter
public abstract class BasicInitDatas<C, D> implements InitDatas {

  private Map<String, C> initDatas = Maps.newHashMap();
  private Map<String, D> dbDatas = Maps.newHashMap();

  @Override
  public boolean canBeModified(String code) {
    if (initDatas.containsKey(code)) {
      return false;
    }
    return true;
  }

  @Override
  public boolean canBeDeleted(String code) {
    if (initDatas.containsKey(code)) {
      return false;
    }
    return true;
  }

  public void addInitData(String code, C c) {
    initDatas.put(code, c);
  }

  public void addDbData(String code, D d) {
    Objects.requireNonNull(d, "dbData is null");
    dbDatas.put(code, d);
  }

  public D getDbData(String code) {
    return dbDatas.get(code);
  }
}
