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
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserVO;
import io.github.kylinhunter.plat.core.init.data.UserInitDatas;
import io.github.kylinhunter.plat.core.init.initializer.DefaultUsers;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.web.auth.PasswordUtil;
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
public class UserSaveOrUpdateInterceptor
    extends SaveOrUpdateInterceptor<
    User, UserReqCreate, UserReqUpdate, UserResp, UserVO, UserReqQuery> {

  private final UserInitDatas userInitData;

  @Override
  public void saveOrUpdateBefore(UserVO vo) {
    if (vo.getId().equals(DefaultUsers.ADMIN_USER_ID)) {
      throw new ParamException("can't save or update  sys user:" + vo.getId());
    }

    super.saveOrUpdateBefore(vo);

    String password = vo.getPassword();
    if (!StringUtils.isEmpty(password)) {
      vo.setPassword(PasswordUtil.encode(password));
    } else {
      throw new ParamException("invalid password");
    }


  }

  @Override
  public User before(UserReqUpdate reqUpdate, boolean tenantSupported, User entity) {
    if (!userInitData.canBeModified(entity.getUserName())) {
      throw new ParamException("invalid user name:" + entity.getUserName());
    }
    return super.before(reqUpdate, tenantSupported, entity);
  }
}
