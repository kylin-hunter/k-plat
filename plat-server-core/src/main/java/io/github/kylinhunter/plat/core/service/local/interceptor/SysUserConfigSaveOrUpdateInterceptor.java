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
import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.SysUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigVO;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 */
@Component
@RequiredArgsConstructor
public class SysUserConfigSaveOrUpdateInterceptor
    extends SaveOrUpdateInterceptor<
        SysUserConfig,
        SysUserConfigReqCreate,
        SysUserConfigReqUpdate,
        SysUserConfigResp,
        SysUserConfigVO,
        SysUserConfigReqQuery> {

  @Override
  protected void saveOrUpdateBefore(SysUserConfigVO vo) {
    super.saveOrUpdateBefore(vo);
    final String userId = vo.getUserId();
    UserContext userContext = userContextHandler.get(true);
    if (!StringUtils.isEmpty(userId)) {
      if (!userId.equals(userContext.getUserId()) && !userContext.isSuperAdmin()) {
        throw new ParamException("not admin ");
      }
    } else {
      vo.setUserId(userContext.getUserId());
    }
  }
}
