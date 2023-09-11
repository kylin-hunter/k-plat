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
package io.github.kylinhunter.plat.storage.exception;

import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.commons.exception.info.ErrInfo;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 14:03
 */
public class StorageException extends BizException {
  public StorageException(String message, Throwable cause) {
    this(StorageErrInfoCustomizer.STORAGE_ERROR, message, cause);
  }

  public StorageException(ErrInfo errInfo) {
    this(errInfo, "");
  }

  public StorageException(String message) {
    this(StorageErrInfoCustomizer.STORAGE_ERROR, message);
  }

  public StorageException(Throwable cause) {
    this(StorageErrInfoCustomizer.STORAGE_ERROR, cause);
  }

  public StorageException(ErrInfo errInfo, String message, Throwable cause) {
    super(errInfo, message, cause);
  }

  public StorageException(ErrInfo errInfo, String message) {
    super(errInfo, message);
  }

  public StorageException(ErrInfo errInfo, Throwable cause) {
    super(errInfo, "", cause);
  }
}
