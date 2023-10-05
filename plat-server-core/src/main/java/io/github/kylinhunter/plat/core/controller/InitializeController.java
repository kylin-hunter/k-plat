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
package io.github.kylinhunter.plat.core.controller;

import io.github.kylinhunter.plat.core.init.SystemDataInitializer;
import io.github.kylinhunter.plat.web.response.DefaultResponse;
import io.github.kylinhunter.plat.web.response.Response;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-03 23:19
 */
@RestController
@RequestMapping("/initialize")
@Api(value = "Tenant相关接口")
@RequiredArgsConstructor
public class InitializeController {

  private final SystemDataInitializer systemDataInitializer;

  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public Response<Boolean> initialize() {
    return new DefaultResponse<>(systemDataInitializer.init(true));
  }
}
