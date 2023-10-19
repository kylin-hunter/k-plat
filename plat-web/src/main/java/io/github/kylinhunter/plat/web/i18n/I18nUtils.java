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

import io.github.kylinhunter.commons.lang.strings.StringUtil;
import io.github.kylinhunter.commons.util.ObjectValues;
import java.util.Locale;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 15:13
 */
@Slf4j
public class I18nUtils {

  private static MessageSource messageSource;

  public I18nUtils(MessageSource messageSource) {
    I18nUtils.messageSource = messageSource;
    LocaleContextHolder.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
  }

  /**
   * @param key key
   * @return java.lang.String
   * @title get
   * @description get
   * @author BiJi'an
   * @date 2023-10-19 23:54
   */
  public static String get(Object key) {
    return get(key, null, null);
  }

  /**
   * @param key  key
   * @param args args
   * @return java.lang.String
   * @title get
   * @description get
   * @author BiJi'an
   * @date 2023-10-19 18:43
   */

  public static String get(Object key, Object args) {
    return get(key, args, null);
  }

  /**
   * @param code         code
   * @param args         args
   * @param defaultValue defaultValue
   * @return java.lang.String
   * @title get
   * @description get
   * @author BiJi'an
   * @date 2023-10-19 18:43
   */
  public static String get(Object code, Object args, String defaultValue) {
    Objects.requireNonNull(code);
    String key = ObjectValues.getString(code);

    Object[] params = null;
    if (args != null) {
      if (args instanceof Object[]) {
        params = (Object[]) args;
      } else {
        params = new Object[]{args};
      }
    }

    try {
      Locale locale = LocaleContextHolder.getLocale();
      String value = messageSource.getMessage(key, params, locale);
      log.info("get in :" + locale + ":" + value);
      if (!StringUtil.isEmpty(value)) {
        return value.trim();
      }
    } catch (Exception e) {
      log.warn("invalid msg={}", e.getMessage());
    }
    return defaultValue;
  }
}
