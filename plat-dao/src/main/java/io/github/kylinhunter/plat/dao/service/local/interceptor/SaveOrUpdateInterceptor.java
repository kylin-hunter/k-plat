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
package io.github.kylinhunter.plat.dao.service.local.interceptor;

import io.github.kylinhunter.commons.utils.bean.BeanCopyUtils;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.bean.vo.VO;
import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqPage;
import io.github.kylinhunter.plat.api.bean.vo.response.single.Resp;
import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 23:40
 */
public class SaveOrUpdateInterceptor<
        T extends BaseEntity,
        C extends ReqCreate,
        U extends ReqUpdate,
        Z extends Resp,
        V extends VO,
        Q extends ReqPage>
    extends BasicInterceptor<T, C, U, Z, V, Q> {

  private final String[] createSkipProperties =
      new String[] {
        "sysTenantId",
        "sysCreatedUserId",
        "sysCreatedUserName",
        "sysCreatedTime",
        "sysUpdateUserId",
        "sysUpdateUserName",
        "sysUpdateTime",
        "sysDeleteFlag",
        "sysOpLock"
      };

  private final String[] updateSkipProperties =
      new String[] {
        "id",
        "sysTenantId",
        "sysCreatedUserId",
        "sysCreatedUserName",
        "sysCreatedTime",
        "sysUpdateUserId",
        "sysUpdateUserName",
        "sysUpdateTime",
        "sysDeleteFlag",
        "sysOpLock",
        "code"
      };

  protected void saveOrUpdateBefore(V vo) {}

  public Z saveOrUpdateAfter(V vo, Z z) {
    return z;
  }

  @SuppressWarnings("unchecked")
  public T before(C c, boolean tenantSupported, T entity) {
    if (tenantSupported) {
      this.checkAndGetTenantId();
    }
    saveOrUpdateBefore((V) c);
    BeanCopyUtils.copyProperties(c, entity, createSkipProperties);
    this.setCreateMsg(c, entity);
    return entity;
  }

  @SuppressWarnings("unchecked")
  public Z after(C c, T enity, Z response) {

    BeanCopyUtils.copyProperties(enity, response);
    return saveOrUpdateAfter((V) c, response);
  }

  @SuppressWarnings("unchecked")
  public T before(U u, boolean tenantSupported, T entity) {
    if (tenantSupported) {
      final String tenantId = this.checkAndGetTenantId();
      checkTenantData(tenantId, entity);
    }
    saveOrUpdateBefore((V) u);
    BeanCopyUtils.copyProperties(u, entity, updateSkipProperties);
    this.setUpdateMsg(u, entity);
    return entity;
  }

  @SuppressWarnings("unchecked")
  public Z after(U u, T entity, Z response) {

    BeanCopyUtils.copyProperties(entity, response);
    return saveOrUpdateAfter((V) u, response);
  }
}
