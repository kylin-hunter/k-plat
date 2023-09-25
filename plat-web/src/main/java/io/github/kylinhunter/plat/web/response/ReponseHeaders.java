package io.github.kylinhunter.plat.web.response;

import io.github.kylinhunter.commons.io.Charsets;
import io.github.kylinhunter.plat.web.servlet.Headers;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2023-09-26 01:28
 */
@Component
public class ReponseHeaders extends Headers {


  /**
   * @param body    body
   * @param headers headers
   * @param length  length
   * @return java.lang.String
   * @throws
   * @title getBodyAsString
   * @description getBodyAsString
   * @author BiJi'an
   * @date 2023-09-26 01:48
   */
  public String getBodyAsString(byte[] body, Map<String, Collection<String>> headers, int length) {
    Charset charset = getCharset(headers, Charsets.UTF_8);
    return getResponseBody(body, charset, length);
  }

  /**
   * @param body    body
   * @param charset charset
   * @param length  length
   * @return java.lang.String
   * @throws
   * @title getResponseBody
   * @description getResponseBody
   * @author BiJi'an
   * @date 2023-09-26 01:48
   */
  private static String getResponseBody(byte[] body, Charset charset, int length) {
    if (length <= 0 || body.length < length) {
      return new String(body, charset);
    }
    return getResponseBodyPreview(body, charset, length / 2);
  }

  /**
   * @param body    body
   * @param charset charset
   * @param charLen charLen
   * @return java.lang.String
   * @throws
   * @title getResponseBodyPreview
   * @description getResponseBodyPreview
   * @author BiJi'an
   * @date 2023-09-26 01:48
   */
  private static String getResponseBodyPreview(byte[] body, Charset charset, int charLen) {
    try (Reader reader = new InputStreamReader(new ByteArrayInputStream(body), charset)) {

      CharBuffer result = CharBuffer.allocate(charLen);
      reader.read(result);
      result.flip();
      return result.toString() + "... (" + body.length + " bytes)";
    } catch (IOException e) {
      return e.toString() + ", failed to parse response";
    }
  }
}