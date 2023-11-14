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
package io.github.kylinhunter.plat.server.core.controller;

import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserVO;
import io.github.kylinhunter.plat.server.core.service.local.UserService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-06 22:29
 */
@RequestMapping("/api/v1/core/users")
@Api(value = "User相关接口")
@RequiredArgsConstructor
@RestController
public class UserController
    extends CommonCurdController<
        UserService, UserReqCreate, UserReqUpdate, UserResp, UserVO, UserReqQuery, User> {}
