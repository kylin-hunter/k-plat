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
package io.github.kylinhunter.plat.api.auth.context;

import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-11 02:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultUserContext implements UserContext, Serializable {

  private int userType;

  private String userId = "";

  private String userName = "";

  private String nickName = "";

  private String realName = "";

  private String tenantId = "";
  private String tenantUserId = "";

  public DefaultUserContext(User user) {
    BeanUtils.copyProperties(user, this);
    this.userId = user.getId();
    this.userType = user.getType();
  }

  public DefaultUserContext(UserContext userContext) {
    BeanUtils.copyProperties(userContext, this);
  }
}
