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
package io.github.kylinhunter.plat.core.service.local;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogTree;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;

/**
 * TenantCatalogService 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-17
 */
public interface TenantCatalogService
    extends CommonService<
        TenantCatalog,
        TenantCatalogReqCreate,
        TenantCatalogReqUpdate,
        TenantCatalogResp,
        TenantCatalogVO,
        TenantCatalogReqQuery> {
  TenantCatalog queryByCode(int type, String code);

  TenantCatalogTree tree(int type);
}
