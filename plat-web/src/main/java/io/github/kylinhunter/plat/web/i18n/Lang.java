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
package io.github.kylinhunter.plat.web.i18n;

import io.github.kylinhunter.commons.lang.EnumUtils;
import java.util.Locale;
import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-07 16:34
 */
@Getter
public enum Lang implements EnumUtils.EnumCode {
  ZH(1, "中文", Locale.CHINESE),
  EN(2, "英文", Locale.ENGLISH);
  private final int code;
  private final String name;
  private final Locale locale;

  Lang(int code, String name, Locale locale) {
    this.code = code;
    this.name = name;
    this.locale = locale;
  }
}
