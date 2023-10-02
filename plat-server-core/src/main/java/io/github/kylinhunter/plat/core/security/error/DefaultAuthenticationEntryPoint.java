package io.github.kylinhunter.plat.core.security.error;

import io.github.kylinhunter.plat.web.exception.WebErrInfoCustomizer;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 23:48
 */
@RequiredArgsConstructor
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {
  private final ResponseWriter responseWriter;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException e)
      throws IOException {
    responseWriter.writeErr(WebErrInfoCustomizer.AUTH_NO_PERMISSION_ANONYMOUS, "no permission for anonymous","");

  }
}
