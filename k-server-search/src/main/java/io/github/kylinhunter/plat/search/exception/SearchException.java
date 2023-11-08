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
package io.github.kylinhunter.plat.search.exception;

import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.commons.exception.info.ErrInfo;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 14:03
 */
public class SearchException extends BizException {
  public SearchException(String message, Throwable cause) {
    this(SearchErrInfos.STORAGE_ERROR, message, cause);
  }

  public SearchException(ErrInfo errInfo) {
    this(errInfo, "");
  }

  public SearchException(String message) {
    this(SearchErrInfos.STORAGE_ERROR, message);
  }

  public SearchException(Throwable cause) {
    this(SearchErrInfos.STORAGE_ERROR, cause);
  }

  public SearchException(ErrInfo errInfo, String message, Throwable cause) {
    super(errInfo, message, cause);
  }

  public SearchException(ErrInfo errInfo, String message) {
    super(errInfo, message);
  }

  public SearchException(ErrInfo errInfo, Throwable cause) {
    super(errInfo, "", cause);
  }
}
