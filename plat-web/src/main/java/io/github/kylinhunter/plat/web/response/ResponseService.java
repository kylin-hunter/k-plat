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
package io.github.kylinhunter.plat.web.response;

import io.github.kylinhunter.commons.exception.ExceptionHelper;
import io.github.kylinhunter.commons.exception.common.KRuntimeException;
import io.github.kylinhunter.plat.web.i18n.I18nUtils;
import io.github.kylinhunter.plat.web.trace.WebTraceHolder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/26
 */
@Data
@Slf4j
public class ResponseService {

  /**
   * @param e e
   * @return io.github.kylinhunter.plat.commons.web.response.DefaultResponse
   * @title toResponse
   * @description
   * @author BiJi'an
   * @date 2021/8/1 4:00 上午
   */
  public Response<?> toResponse(KRuntimeException e, boolean debug) {

    Response<?> response = new DefaultResponse<>();
    response.setCode(e.getErrInfo().getCode());
    String errMsg = this.getI18nErrMsg(e, debug);
    if (!StringUtils.isBlank(errMsg)) {
      response.setMsg(errMsg);
    } else {
      response.setMsg(ExceptionHelper.getMessage(e, debug, 1000));
    }
    response.setTrace(WebTraceHolder.get());

    return response;
  }

  private String getI18nErrMsg(KRuntimeException e, boolean debug) {
    String errMsg;
    if (debug) {
      errMsg = I18nUtils.get(e.getErrInfo().getCode(), e.getExtra());
    } else {
      errMsg = I18nUtils.get(e.getErrInfo().getCode());
      if (!StringUtils.isEmpty(errMsg)) {
        errMsg = errMsg.replaceAll("\\{.*\\d.*\\}", "").trim();
      }
    }
    return errMsg;
  }
}
