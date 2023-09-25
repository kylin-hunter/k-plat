package io.github.kylinhunter.plat.web.servlet;

import io.github.kylinhunter.commons.io.Charsets;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BiJi'an
 * @description
 * @date 2023-09-26 01:20
 */
public class Headers {

  /**
   * @param headers headers
   * @return java.nio.charset.Charset
   * @throws
   * @title getCharset
   * @description getCharset
   * @author BiJi'an
   * @date 2023-09-26 01:26
   */
  public Charset getCharset(Map<String, Collection<String>> headers, Charset defaultCharset) {

    Collection<String> strings = headers.get("content-type");
    if (strings == null || strings.isEmpty()) {
      return defaultCharset;
    }

    Pattern pattern = Pattern.compile(".*charset=([^\\s|^;]+).*");
    Matcher matcher = pattern.matcher(strings.iterator().next());
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