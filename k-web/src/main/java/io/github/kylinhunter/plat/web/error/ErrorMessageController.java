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

import io.github.kylinhunter.plat.web.response.DefaultResponse;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author BiJi'an
 * @description 404 500 异常处理
 * @date 2021/7/30
 */
@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorMessageController extends BasicErrorController {

  public ErrorMessageController(
      ErrorAttributes errorAttributes,
      ErrorProperties errorProperties,
      List<ErrorViewResolver> errorViewResolvers) {
    super(errorAttributes, errorProperties, errorViewResolvers);
  }

  @RequestMapping
  @Override
  public ResponseEntity error(HttpServletRequest request) {
    HttpStatus status = getStatus(request);
    DefaultResponse<?> defaultResponse;
    if (status == HttpStatus.NOT_FOUND) {
      defaultResponse = new DefaultResponse<>(404, "NOT_FOUND");
    } else if (status == HttpStatus.FORBIDDEN) {
      defaultResponse = new DefaultResponse<>(403, "FORBIDDEN");
    } else {
      defaultResponse = new DefaultResponse<>(500, "Internal Server Error");
    }
    return new ResponseEntity<>(defaultResponse, status);
  }
}
