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
   * @param contentTypes   contentTypes
   * @param defaultCharset defaultCharset
   * @return java.nio.charset.Charset
   * @throws
   * @title getCharsetFromContentType
   * @description getCharsetFromContentType
   * @author BiJi'an
   * @date 2023-09-26 19:27
   */
  public static Charset getCharsetFromContentType(Collection<String> contentTypes,
      Charset defaultCharset) {
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