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
import io.github.kylinhunter.commons.exception.embed.biz.DBException;
import io.github.kylinhunter.plat.api.auth.context.UserContext;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.bean.vo.VO;
import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqPage;
import io.github.kylinhunter.plat.api.bean.vo.request.Req;
import io.github.kylinhunter.plat.api.bean.vo.response.single.Resp;
import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 22:59
 */
public class BasicInterceptor<
    T extends BaseEntity,
    C extends ReqCreate,
    U extends ReqUpdate,
    Z extends Resp,
    V extends VO,
    Q extends ReqPage> {

  @Autowired(required = false)
  protected TraceHolder traceHolder;

  protected void setCreateMsg(Req req, T entity) {

    UserContext userContext = traceHolder.get().getUserContext();
    entity.setSysTenantId(userContext.getTenantId());
    entity.setSysCreatedUserId(userContext.getUserId());
    entity.setSysCreatedUserName(userContext.getUserName());
    entity.setSysCreatedTime(LocalDateTime.now());

    entity.setSysUpdateUserId(userContext.getUserId());
    entity.setSysUpdateUserName(userContext.getUserName());
    entity.setSysUpdateTime(LocalDateTime.now());

    entity.setSysDeleteFlag(false);
  }

  protected void setUpdateMsg(Req req, T entity) {
    UserContext userContext = traceHolder.get().getUserContext();

    entity.setSysUpdateUserId(userContext.getUserId());
    entity.setSysUpdateUserName(userContext.getUserName());
    entity.setSysUpdateTime(LocalDateTime.now());
  }

  protected void checkSameTenant(String tenantId) {
    String clientTenantId = checkTenantId();
    if (!clientTenantId.equals(tenantId)) {
      throw new DBException("tenantId invalid：" + clientTenantId + "/" + tenantId);
    }
  }

  protected void checkSameTenant(List<T> entities) {
    String tenantId = checkTenantId();
    entities.forEach(
        entity -> {
          if (!tenantId.equals(entity.getSysTenantId())) {
            throw new DBException("tenantId invalid：" + tenantId + "/" + entity.getSysTenantId());
          }
        });
  }

  protected String checkTenantId() {
    String tenantId = traceHolder.get().getUserContext().getTenantId();
    if (StringUtils.isEmpty(tenantId)) {
      throw new DBException("tenantId is emtpy");
    }
    return tenantId;
  }

  public String checkSelfUser(String userId) {
    UserContext userContext = traceHolder.get().getUserContext();
    if (!userContext.getUserId().equals(userId)) {
      throw new ParamException("no access to other users' data");
    }
    return userContext.getUserId();
  }

  public void checkSelfUser(List<String> userIds) {
    UserContext userContext = traceHolder.get().getUserContext();
    userIds.forEach(userId -> {
      if (!userContext.getUserId().equals(userId)) {
        throw new ParamException("no access to other users' data");
      }
    });

  }

  public void checkSelfTenant(String tenantId) {
    UserContext userContext = traceHolder.get().getUserContext();
    if (!userContext.getTenantId().equals(tenantId)) {
      throw new ParamException("no access to other tenant' data");
    }
  }
}
