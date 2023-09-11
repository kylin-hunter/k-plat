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

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;

/**
 * @author BiJi'an
 * @description
 * @date 2022/01/01
 */
public interface UserContextHandler {
  /**
   * @return io.github.kylinhunter.plat.api.context.UserContext
   * @title create
   * @description
   * @author BiJi'an
   * @date 2022-06-11 00:41
   */
  UserContext create(Token token);

  /**
   * @return io.github.kylinhunter.plat.api.context.UserContext
   * @title create
   * @description
   * @author BiJi'an
   * @date 2022-06-11 00:41
   */
  UserContext create(User user);

  /**
   * @return io.github.kylinhunter.plat.api.context.UserContext
   * @title get get
   * @description
   * @author BiJi'an
   * @date 2022-06-11 00:40
   */
  UserContext get();

  UserContext get(boolean check);

  /**
   * @return void
   * @title remove
   * @description
   * @author BiJi'an
   * @date 2022-06-11 00:41
   */
  void remove();
}
