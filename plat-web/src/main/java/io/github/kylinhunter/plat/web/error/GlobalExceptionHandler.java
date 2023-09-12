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
package io.github.kylinhunter.plat.web.error;

import io.github.kylinhunter.commons.exception.ExceptionConvertor;
import io.github.kylinhunter.commons.utils.json.JsonOptions;
import io.github.kylinhunter.commons.utils.json.JsonUtils;
import io.github.kylinhunter.plat.web.response.DefaultResponse;
import io.github.kylinhunter.plat.web.response.ResponseService;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/1
 */
@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final ResponseService responseService;

  private final ResponseWriter responseWriter;

  /**
   * @param req req
   * @param rsp rsp
   * @param globalException globalException
   * @param model model
   * @return java.lang.Object
   * @title handler
   * @description
   * @author BiJi'an
   * @date 2021/8/1 4:00 上午
   */
  @ExceptionHandler(value = Exception.class)
  public Object handler(
      HttpServletRequest req, HttpServletResponse rsp, Exception globalException, Model model) {

    try {
      log.error("global error", globalException);
      DefaultResponse<?> response =
          responseService.toResponse(ExceptionConvertor.convert(globalException));
      String responseJson = JsonUtils.writeToString(response, JsonOptions.NO_FAIL);
      log.error(req.getRequestURI() + "'s response:" + responseJson);
      responseWriter.writeJson(responseJson);
    } catch (Exception e) {
      log.error("global_exception_handler_error", e);
    }
    return null;
  }
}
