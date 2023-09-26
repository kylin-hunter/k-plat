package io.github.kylinhunter.plat.web.feign;

import feign.Response;
import io.github.kylinhunter.commons.io.Charsets;
import io.github.kylinhunter.commons.io.IOUtil;
import io.github.kylinhunter.plat.web.exception.WebException;
import io.github.kylinhunter.plat.web.servlet.HeaderUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2023-09-26 17:14
 */
@Component
public class FeignComponent {

  private static final int MAX_ERR_BODY_LENGTH = 400;

  /**
   * @param response response
   * @return java.lang.String
   * @throws
   * @title getResponseBody
   * @description getResponseBody
   * @author BiJi'an
   * @date 2023-09-26 19:35
   */
  public String getResponseBody(Response response) {

    try (InputStream in = response.body().asInputStream()) {

      Collection<String> contextTypes = response.headers().get(HeaderUtils.CONTENT_TYPE);
      Charset charset = HeaderUtils.getCharsetFromContentType(contextTypes, Charsets.UTF_8);
      return IOUtil.toString(in, charset, MAX_ERR_BODY_LENGTH);
    } catch (IOException e) {
      throw new WebException(e);
    }

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
  private String getResponseBody(byte[] body, Charset charset, int length) {
    if (length <= 0 || body.length < length) {
      return new String(body, charset);
    }
    int charLen = length / 2;
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