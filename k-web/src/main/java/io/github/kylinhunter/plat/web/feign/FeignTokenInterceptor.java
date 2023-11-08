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
package io.github.kylinhunter.plat.web.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.kylinhunter.plat.api.web.request.RequestConst;
import io.github.kylinhunter.plat.web.trace.WebTraceHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-19 00:23
 */
@Slf4j
@RequiredArgsConstructor
public class FeignTokenInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {

    String feignToken = RequestConst.BEARER + WebTraceHolder.get().getVerifyToken().getToken();

    requestTemplate.header(RequestConst.HEADER_AUTH, feignToken);
  }
}
