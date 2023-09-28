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
package io.github.kylinhunter.plat.web.exception;

import io.github.kylinhunter.commons.exception.info.ErrInfo;
import io.github.kylinhunter.commons.exception.info.ErrInfoAware;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 00:53
 */
@ErrInfoAware
public class WebErrInfoCustomizer {


  static int WEB_BASE_CODE = 60000;
  public static final ErrInfo WEB_ERROR = new ErrInfo(++WEB_BASE_CODE);
  public static final ErrInfo WEB_NOT_SUPPORTED = new ErrInfo(++WEB_BASE_CODE);
  public static final ErrInfo WEB_NO_HANDLER_FOUND = new ErrInfo(++WEB_BASE_CODE);

  static int AUTH_BASE_CODE = 60100;

  public static final ErrInfo AUTH_ERROR = new ErrInfo(++AUTH_BASE_CODE);
  public static final ErrInfo AUTH_TOKEN_CREATE_ERROR = new ErrInfo(++AUTH_BASE_CODE);
  public static final ErrInfo AUTH_TOKEN_VERIFY_NOT_FOUND = new ErrInfo(++AUTH_BASE_CODE);
  public static final ErrInfo AUTH_TOKEN_VERIFY_EXPIRED = new ErrInfo(++AUTH_BASE_CODE);
  public static final ErrInfo AUTH_TOKEN_VERIFY_INVALID = new ErrInfo(++AUTH_BASE_CODE);
  public static final ErrInfo AUTH_TOKEN_VERIFY_ERROR = new ErrInfo(++AUTH_BASE_CODE);


  static int FEIGN_ERR_CODE = 60200;

  public static final ErrInfo FEIGN_ERROR = new ErrInfo(++FEIGN_ERR_CODE);
  public static final ErrInfo FEIGN_REQUEST_ERROR = new ErrInfo(++FEIGN_ERR_CODE);
  public static final ErrInfo FEIGN_SERVER_ERROR = new ErrInfo(++FEIGN_ERR_CODE);


  static int CIRCUIT_BREAKER_ERR_CODE = 60300;
  public static final ErrInfo LIMIT_EXCEEDS = new ErrInfo(++CIRCUIT_BREAKER_ERR_CODE);
}
