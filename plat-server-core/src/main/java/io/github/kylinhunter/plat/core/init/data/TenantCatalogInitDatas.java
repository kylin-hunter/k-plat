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

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import java.util.UUID;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 */
@Component
@Getter
public class TenantCatalogInitDatas extends BasicInitDatas<TenantCatalogReqCreate, TenantCatalog> {
  public static final String DEFAULT_CODE = "default";
  public static final int DEFAULT_TYPE = 0;
  private TenantCatalogReqCreate DEFAULT = createDefaultCatalog();

  private TenantCatalogReqCreate createDefaultCatalog() {

    TenantCatalogReqCreate reqCreate = new TenantCatalogReqCreate();
    reqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
    reqCreate.setType(DEFAULT_TYPE);
    reqCreate.setCode(DEFAULT_CODE);
    reqCreate.setName(DEFAULT_CODE);
    reqCreate.setStatus(0);

    reqCreate.setLevel(0);
    reqCreate.setPath("0");
    reqCreate.setParentId("0");
    reqCreate.setDescription(DEFAULT_CODE);
    this.addInitData(reqCreate.getCode(), reqCreate);
    return reqCreate;
  }
}
