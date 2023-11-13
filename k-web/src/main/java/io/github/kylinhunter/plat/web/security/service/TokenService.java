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
package io.github.kylinhunter.plat.web.security.service;

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.auth.bean.vo.ReqTenantToken;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;

/**
 * @author BiJi'an
 * @description TokenService
 * @date 2023-10-02 00:42
 */
public interface TokenService {

  String createToken(TokenUserDetails tokenUserDetails);

  String createTenantToken(ReqTenantToken reqTenantToken);

  Token invalidToken();

  TokenUserDetails verify(String token) throws AuthException;
}