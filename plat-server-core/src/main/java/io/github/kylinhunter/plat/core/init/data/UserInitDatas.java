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

import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import io.github.kylinhunter.plat.api.module.core.constants.UserStatus;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
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
public class UserInitDatas extends BasicInitDatas<UserReqCreate, User> {
  public static final String USER_ADMIN = "admin";
  public static final String USER_TEST = "test";

  private UserReqCreate userAdmin = createUserAdmin();
  private UserReqCreate userTest = createUserTest();

  private UserReqCreate createUserAdmin() {

    UserReqCreate userReqCreate = new UserReqCreate();
    userReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
    userReqCreate.setNickName(USER_ADMIN);
    userReqCreate.setRealName(USER_ADMIN);
    userReqCreate.setUserName(USER_ADMIN);
    userReqCreate.setPassword(USER_ADMIN);
    userReqCreate.setSource("0");
    userReqCreate.setType(UserType.SUPER_ADMIN.getCode());
    userReqCreate.setStatus(UserStatus.NORMAL.getCode());
    userReqCreate.setDescription(USER_ADMIN);
    this.addInitData(userReqCreate.getUserName(), userReqCreate);
    return userReqCreate;
  }

  private UserReqCreate createUserTest() {

    UserReqCreate userReqCreate = new UserReqCreate();
    userReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
    userReqCreate.setNickName(USER_TEST);
    userReqCreate.setRealName(USER_TEST);
    userReqCreate.setUserName(USER_TEST);
    userReqCreate.setPassword(USER_TEST);
    userReqCreate.setSource("0");
    userReqCreate.setType(UserType.USER.getCode());
    userReqCreate.setStatus(UserStatus.NORMAL.getCode());
    userReqCreate.setDescription(USER_TEST);
    this.addInitData(userReqCreate.getUserName(), userReqCreate);
    return userReqCreate;
  }
}
