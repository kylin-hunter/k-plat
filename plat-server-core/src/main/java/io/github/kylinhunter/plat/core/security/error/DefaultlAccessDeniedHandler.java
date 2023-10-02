package io.github.kylinhunter.plat.core.security.error;

import io.github.kylinhunter.plat.web.exception.WebErrInfoCustomizer;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 21:56
 */
@RequiredArgsConstructor

public class DefaultlAccessDeniedHandler implements AccessDeniedHandler {

  private final ResponseWriter responseWriter;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) {
    responseWriter.writeErr(WebErrInfoCustomizer.AUTH_NO_PERMISSION,
        "the logoned user no permission", "");
  }
}