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
package io.github.kylinhunter.plat.core.service.local.interceptor;

import io.github.kylinhunter.commons.exception.embed.ParamException;
import io.github.kylinhunter.plat.api.bean.vo.constants.ReqType;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogVO;
import io.github.kylinhunter.plat.api.module.core.constants.init.DefaultTenantCatalogs;
import io.github.kylinhunter.plat.core.dao.mapper.TenantCatalogMapper;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 */
@Component
@RequiredArgsConstructor
public class TenantCatalogSaveOrUpdateInterceptor
    extends SaveOrUpdateInterceptor<
        TenantCatalog,
        TenantCatalogReqCreate,
        TenantCatalogReqUpdate,
        TenantCatalogResp,
        TenantCatalogVO,
        TenantCatalogReqQuery> {

  private final TenantCatalogMapper tenantCatalogMapper;

  @Override
  protected void saveOrUpdateBefore(TenantCatalogVO vo, TenantCatalog entity) {
    super.saveOrUpdateBefore(vo, entity);
    String code = vo.getCode();

    if (DefaultTenantCatalogs.ROOT_CODE.equals(code)) {
      throw new ParamException("root data: " + code + " is not allowed to be modified");
    } else {
      String parentId = vo.getParentId();
      TenantCatalog parent = this.tenantCatalogMapper.selectById(parentId);
      if (parent == null) {
        throw new ParamException("invalid parentId:" + parentId);
      }
      vo.setType(parent.getType());
      vo.setLevel(parent.getLevel() + 1);
      vo.setPath(parent.getPath() + "_" + parentId);
      vo.setStatus(parent.getStatus());
      if (vo.getReqType() == ReqType.CREATE) {
        int count = tenantCatalogMapper.selectCountByParentId(parentId);
        vo.setSort(count + 1);
      } else {
        vo.setSort(entity.getSort());
      }
    }
  }
}
