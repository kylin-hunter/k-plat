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
package io.github.kylinhunter.plat.core.init.initializer;

import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.core.init.data.UserInitDatas;
import io.github.kylinhunter.plat.core.service.local.UserService;
import io.github.kylinhunter.plat.web.auth.PasswordUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:53
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Setter
@Getter
public class Order01UserInitializer extends BasicInitializer {
  private final UserInitDatas userInitData;
  private final UserService userService;
  private final UserContextHandler userContextHandler;

  @Override
  public void init() {

    userInitData
        .getInitDatas()
        .values()
        .forEach(
            userCreate -> {
              final String userName = userCreate.getUserName();
              User user = userService.findByUserName(userName);
              if (user != null) {
                log.info("default userCreate {} exist", userName);
                userInitData.addDbData(userName, user);
              } else {
                user = new User();
                BeanUtils.copyProperties(userCreate, user);
                user.setPassword(PasswordUtil.encode(user.getPassword()));
                userService.save(user);
                log.info("default userCreate {} created", userName);
                userInitData.addDbData(userName, userService.findByUserName(userName));
              }
            });
    User userAdmin = userInitData.getDbData(UserInitDatas.USER_ADMIN);
    initUserContext(userAdmin);
  }

  private UserContext initUserContext(User user) {
    UserContext userContext = userContextHandler.create(user);
    log.info("init userContext {}", userContext);
    return userContext;
  }
}
