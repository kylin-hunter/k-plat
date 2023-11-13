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

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.collect.Maps;
import io.github.kylinhunter.commons.exception.ExceptionFinder;
import io.github.kylinhunter.commons.exception.explain.AbstractExplainerSupplier;
import io.github.kylinhunter.commons.exception.explain.ExplainResult;
import io.github.kylinhunter.commons.exception.info.ErrInfos;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/1
 */
@Slf4j
public class WebErrExplainer extends AbstractExplainerSupplier {

  @Override
  public void explain() {

    this.addExplainer(BindException.class)
        .explain(
            e -> {
              Map<String, String> errMsgs = Maps.newHashMap();
              if (e.hasErrors()) {
                for (ObjectError objectError : e.getAllErrors()) {
                  if (objectError instanceof FieldError) {
                    errMsgs.put(
                        ((FieldError) objectError).getField(), objectError.getDefaultMessage());
                  }
                }
              }
              return new ExplainResult(ErrInfos.PARAM, errMsgs.toString());
            });

    this.addExplainer(MethodArgumentNotValidException.class)
        .explain(
            e -> {
              Map<String, String> errMsgs = Maps.newHashMap();
              if (e.getBindingResult().hasErrors()) {
                for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
                  if (objectError instanceof FieldError) {
                    errMsgs.put(
                        ((FieldError) objectError).getField(), objectError.getDefaultMessage());
                  }
                }
              }
              return new ExplainResult(ErrInfos.PARAM, errMsgs.toString());
            });

    this.addExplainer(HttpRequestMethodNotSupportedException.class)
        .explain(e -> new ExplainResult(WebErrInfos.WEB_NOT_SUPPORTED, e.getMessage()));

    this.addExplainer(NoHandlerFoundException.class)
        .explain(e -> new ExplainResult(WebErrInfos.WEB_NO_HANDLER_FOUND, e.getMessage()));

    this.addExplainer(InvalidFormatException.class)
        .explain(e -> new ExplainResult(ErrInfos.FORMAT, e.getMessage()));

    this.addExplainer(HttpMessageNotReadableException.class)
        .explain(e -> new ExplainResult(ErrInfos.FORMAT, e.getMessage()));

    // feign err
    this.addExplainer(feign.RetryableException.class)
        .explain(e -> new ExplainResult(WebErrInfos.FEIGN_ERROR, e.getMessage()));

    // sentinel error

    this.addExplainer(BlockException.class)
        .explain(e -> new ExplainResult(WebErrInfos.LIMIT_EXCEEDS, e.getMessage()));

    this.addExplainer(UndeclaredThrowableException.class)
        .explain(
            e -> {
              BlockException ex = ExceptionFinder.find(e, true, BlockException.class);

              if (ex != null) {
                return new ExplainResult(WebErrInfos.LIMIT_EXCEEDS, e.getMessage());
              }
              return new ExplainResult(WebErrInfos.WEB_ERROR);
            });

    this.addExplainer(AccessDeniedException.class)
        .explain(e -> new ExplainResult(WebErrInfos.AUTH_NO_PERMISSION));
  }
}
