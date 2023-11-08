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
public class WebException extends BizException {
  public WebException(String message, Throwable cause) {
    this(WebErrInfos.WEB_ERROR, message, cause);
  }

  public WebException(ErrInfo errInfo) {
    this(errInfo, "");
  }

  public WebException(String message) {
    this(WebErrInfos.WEB_ERROR, message);
  }

  public WebException(Throwable cause) {
    this(WebErrInfos.WEB_ERROR, cause);
  }

  public WebException(ErrInfo errInfo, String message, Throwable cause) {
    super(errInfo, message, cause);
  }

  public WebException(ErrInfo errInfo, String message) {
    super(errInfo, message);
  }

  public WebException(ErrInfo errInfo, Throwable cause) {
    super(errInfo, "", cause);
  }
}
