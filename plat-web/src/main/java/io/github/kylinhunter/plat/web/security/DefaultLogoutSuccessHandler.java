package io.github.kylinhunter.plat.web.security;

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.web.exception.WebErrInfoCustomizer;
import io.github.kylinhunter.plat.web.response.DefaultResponse;
import io.github.kylinhunter.plat.web.response.Response;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-15 01:43
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultLogoutSuccessHandler implements LogoutSuccessHandler {

  private final TokenService tokenService;
  private final ResponseWriter responseWriter;
  private static final Response<String> successResp = new DefaultResponse<>("log out seccess");

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    try {
      Token token = tokenService.invalidToken();
      if (token != null) {
        log.info("{}/{} log out success", token.getUserId(), token.getUserName());
      }
      responseWriter.writeJson(successResp);
    } catch (Exception e) {
      log.error("logout error", e);
      responseWriter.writeErr(WebErrInfoCustomizer.AUTH_ERROR, "logout error", "");

    }
  }
}