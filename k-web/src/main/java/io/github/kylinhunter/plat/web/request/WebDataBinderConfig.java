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
package io.github.kylinhunter.plat.web.request;

import com.google.common.collect.Lists;
import io.github.kylinhunter.commons.date.DateUtils;
import io.github.kylinhunter.plat.api.bean.filter.ReqFilter;
import io.github.kylinhunter.plat.api.bean.sort.ReqSort;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-20 15:02
 */
@ControllerAdvice
public class WebDataBinderConfig {

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(
        Date.class,
        new PropertyEditorSupport() {
          @Override
          public void setAsText(String text) throws IllegalArgumentException {
            // 日期解析方法,将对应的text解析成自己需要的格式
            if (!StringUtils.isEmpty(text)) {
              LocalDateTime localDateTime = DateUtils.parse(text);
              this.setValue(DateUtils.toDate(localDateTime));
            }
          }
        });

    binder.registerCustomEditor(
        LocalDateTime.class,
        new PropertyEditorSupport() {
          @Override
          public void setAsText(String text) throws IllegalArgumentException {
            // 日期解析方法,将对应的text解析成自己需要的格式
            if (!StringUtils.isEmpty(text)) {
              this.setValue(DateUtils.parse(text));
            }
          }
        });

    binder.registerCustomEditor(
        LocalDate.class,
        new PropertyEditorSupport() {
          @Override
          public void setAsText(String text) throws IllegalArgumentException {
            // 日期解析方法,将对应的text解析成自己需要的格式
            if (!StringUtils.isEmpty(text)) {
              this.setValue(DateUtils.parseWithDate(text));
            }
          }
        });

    binder.registerCustomEditor(
        List.class,
        "sorts",
        new PropertyEditorSupport() {
          @Override
          public void setAsText(String text) throws IllegalArgumentException {

            if (!StringUtils.isEmpty(text)) {
              String[] textArrs = StringUtils.split(text, ";");
              if (textArrs != null && textArrs.length > 0) {
                List<ReqSort> reqSorts = Lists.newArrayList();
                for (String textArr : textArrs) {
                  String[] fieldAndSort = StringUtils.split(textArr, "@");
                  if (fieldAndSort != null && fieldAndSort.length == 2) {
                    reqSorts.add(new ReqSort(fieldAndSort[0], fieldAndSort[1]));
                  }
                }
                this.setValue(reqSorts);
              }
            }
          }
        });
    binder.registerCustomEditor(
        List.class,
        "filters",
        new PropertyEditorSupport() {
          @Override
          public void setAsText(String text) throws IllegalArgumentException {

            if (!StringUtils.isEmpty(text)) {

              String[] textArrs = StringUtils.split(text, ";");
              if (textArrs != null && textArrs.length > 0) {
                List<ReqFilter> reqFilters = Lists.newArrayList();
                for (String textArr : textArrs) {
                  String[] fieldAndValue = StringUtils.split(textArr, "@");
                  if (fieldAndValue != null && fieldAndValue.length == 2) {
                    reqFilters.add(new ReqFilter(fieldAndValue[0], fieldAndValue[1]));
                  }
                }
                this.setValue(reqFilters);
              }
            }
          }
        });
  }
}
