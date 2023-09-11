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
package io.github.kylinhunter.plat.api.service.cache;

/**
 * @description
 * @author BiJi'an
 * @date 2022-01-01 16:37
 */
public abstract class CommonCacheImp<Z> implements CommonCache<Z> {
  @Override
  public Z get(String id) {
    return null;
  }

  @Override
  public boolean remove(String id) {
    return false;
  }

  @Override
  public boolean exist(String id) {
    return false;
  }

  @Override
  public boolean removeExist(String id) {
    return false;
  }

  @Override
  public boolean removeAll() {
    return false;
  }
}
