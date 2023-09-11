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

import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.commons.exception.info.ErrInfo;

/**
 * @description
 * @author BiJi'an
 * @date 2022-01-01 14:03
 */
public class AuthException extends BizException {
  public AuthException(String message, Throwable cause) {
    this(WebErrInfoCustomizer.AUTH_ERROR, message, cause);
  }

  public AuthException(ErrInfo errInfo) {
    this(errInfo, "");
  }

  public AuthException(String message) {
    this(WebErrInfoCustomizer.AUTH_ERROR, message);
  }

  public AuthException(Throwable cause) {
    this(WebErrInfoCustomizer.AUTH_ERROR, cause);
  }

  public AuthException(ErrInfo errInfo, String message, Throwable cause) {
    super(errInfo, message, cause);
  }

  public AuthException(ErrInfo errInfo, String message) {
    super(errInfo, message);
  }

  public AuthException(ErrInfo errInfo, Throwable cause) {
    super(errInfo, "", cause);
  }
}
