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
package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.VO;

public interface TenantCatalogVO extends VO {
  String getCode();

  void setCode(String code);

  Integer getLevel();

  void setLevel(Integer level);

  String getDescription();

  void setDescription(String description);

  Integer getType();

  void setType(Integer type);

  String getParentId();

  void setParentId(String parentId);

  String getPath();

  void setPath(String path);

  String getName();

  void setName(String name);

  Integer getStatus();

  void setStatus(Integer status);

  Integer getSort();

  void setSort(Integer sort);
}