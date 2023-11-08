package io.github.kylinhunter.plat.web.servlet;

import io.github.kylinhunter.commons.io.Charsets;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeaderUtilsTest {

  @Test
  void getCharset() {
    Charset charset = HeaderUtils.getCharsetFromContentType("text/html;charset=utf-8", null);
    Assertions.assertEquals(Charsets.UTF_8, charset);
    charset = HeaderUtils.getCharsetFromContentType("application/json;charset=utf-8", null);
    Assertions.assertEquals(Charsets.UTF_8, charset);
    charset = HeaderUtils.getCharsetFromContentType("application/json;charset1=utf-8", StandardCharsets.UTF_16BE);
    Assertions.assertEquals(StandardCharsets.UTF_16BE, charset);
    charset = HeaderUtils.getCharsetFromContentType("application/json;charset=utf-81", StandardCharsets.UTF_16BE);
    Assertions.assertEquals(StandardCharsets.UTF_16BE, charset);
    charset = HeaderUtils.getCharsetFromContentType("", StandardCharsets.UTF_16BE);
    Assertions.assertEquals(StandardCharsets.UTF_16BE, charset);


  }
}