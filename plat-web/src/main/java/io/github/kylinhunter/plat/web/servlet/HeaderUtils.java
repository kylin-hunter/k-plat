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
package io.github.kylinhunter.plat.web.servlet;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BiJi'an
 * @description
 * @date 2023-09-26 01:20
 */
public class HeaderUtils {

  public static final String CONTENT_TYPE = "content-type";

  /**
   * @param contentTypes contentTypes
   * @param defaultCharset defaultCharset
   * @return java.nio.charset.Charset
   * @throws
   * @title getCharsetFromContentType
   * @description getCharsetFromContentType
   * @author BiJi'an
   * @date 2023-09-26 19:27
   */
  public static Charset getCharsetFromContentType(
      Collection<String> contentTypes, Charset defaultCharset) {
    if (contentTypes == null || contentTypes.isEmpty()) {
      return defaultCharset;
    }
    return getCharsetFromContentType(contentTypes.iterator().next(), defaultCharset);
  }

  /**
   * @param contentType contentType
   * @return java.nio.charset.Charset
   * @throws
   * @title getCharset
   * @description getCharset
   * @author BiJi'an
   * @date 2023-09-26 01:26
   */
  public static Charset getCharsetFromContentType(String contentType, Charset defaultCharset) {

    if (contentType == null || contentType.isEmpty()) {
      return defaultCharset;
    }

    Pattern pattern = Pattern.compile(".*charset=([^\\s|^;]+).*");
    Matcher matcher = pattern.matcher(contentType);
    if (!matcher.lookingAt()) {
      return defaultCharset;
    }

    String group = matcher.group(1);
    if (!Charset.isSupported(group)) {
      return defaultCharset;
    }
    Charset charset = Charset.forName(group);
    if (charset == null) {
      return defaultCharset;
    }
    return charset;
  }
}
