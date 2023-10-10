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
package io.github.kylinhunter.plat.api.context;

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-11 02:55
 */
@Data
public class DefaultUserContext implements UserContext, Serializable {

  @ApiModelProperty(value = "tenantId", hidden = true)
  private String tenantId = "";

  @ApiModelProperty(value = "userId", hidden = true)
  private String userId = "";

  @ApiModelProperty(value = "userCode", hidden = true)
  private String userCode = "";

  @ApiModelProperty(value = "userName", hidden = true)
  private String userName = "";

  @ApiModelProperty(value = "userType")
  private int userType;


  private Token token;


  public DefaultUserContext(Token token) {
    BeanUtils.copyProperties(token, this);
    this.token = token;
  }

  public DefaultUserContext(User user) {
    BeanUtils.copyProperties(user, this);
    this.userType = user.getType();
    this.userId = user.getId();
  }

  @Override
  public boolean isDummy() {
    return false;
  }

  @Override
  public boolean isSuperAdmin() {
    return UserType.isSuperAdmin(this.userType);
  }
}
