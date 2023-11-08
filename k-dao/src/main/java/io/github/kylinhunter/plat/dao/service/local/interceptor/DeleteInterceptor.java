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

import io.github.kylinhunter.commons.exception.embed.ParamException;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.bean.vo.VO;
import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDeletes;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqPage;
import io.github.kylinhunter.plat.api.bean.vo.response.single.Resp;
import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 23:40
 */
public class DeleteInterceptor<
        T extends BaseEntity,
        C extends ReqCreate,
        U extends ReqUpdate,
        Z extends Resp,
        V extends VO,
        Q extends ReqPage>
    extends BasicInterceptor<T, C, U, Z, V, Q> {

  public void before(ReqDelete reqDelete, boolean tenantSupported, T entity) {
    if (StringUtils.isEmpty(reqDelete.getId())) {
      throw new ParamException("delete id is empty");
    }
    if (tenantSupported) {
      checkSameTenant(entity.getSysTenantId());
    }
    if (!reqDelete.isPhysical()) {
      entity.setSysDeleteFlag(true);
      setUpdateMsg(reqDelete, entity);
    }
  }

  public boolean after(ReqDelete reqDelete, T entity) {
    return true;
  }

  public void before(ReqDeletes reqDeletes, boolean tenantSupported, List<T> entities) {
    if (CollectionUtils.isEmpty(reqDeletes.getIds())) {
      throw new ParamException("delete id is empty");
    }
    if (tenantSupported) {
      checkSameTenant(entities);
    }
    if (!reqDeletes.isPhysical()) {
      entities.forEach(
          entity -> {
            entity.setSysDeleteFlag(true);
            setUpdateMsg(reqDeletes, entity);
          });
    }
  }

  public boolean after(ReqDeletes reqDeletes, List<T> datas) {
    return true;
  }
}
