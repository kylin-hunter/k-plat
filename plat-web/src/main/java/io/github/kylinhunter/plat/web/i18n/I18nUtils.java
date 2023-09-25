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

import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 15:13
 */
@Component
@Slf4j
public class I18nUtils {

  private static MessageSource messageSource;

  public I18nUtils(MessageSource messageSource) {
    I18nUtils.messageSource = messageSource;
    LocaleContextHolder.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
  }

  /**
   * 获取单个国际化翻译值
   */
  public static String get(long errCode) {
    return get(errCode + "", null, null);
  }

  /**
   * 获取单个国际化翻译值
   */
  public static String get(long errCode, Object params) {
    if (params instanceof Object[]) {
      return get(errCode + "", (Object[]) params, null);

    } else {
      return get(errCode + "", null, null);
    }
  }

  /**
   * 获取单个国际化翻译值
   */
  public static String get(String msgKey) {
    return get(msgKey, null, msgKey);
  }

  /**
   * 获取单个国际化翻译值
   */
  public static String get(String msgKey, Object[] args, String defaultValue) {
    try {
      Locale locale = LocaleContextHolder.getLocale();
      String value = messageSource.getMessage(msgKey, args, locale);
      log.info("get in :" + locale + ":" + value);
      if (!StringUtils.isEmpty(value)) {
        return value;
      }
    } catch (Exception e) {
      log.warn("invalid msg={}", e.getMessage());
    }
    return defaultValue;
  }
}
