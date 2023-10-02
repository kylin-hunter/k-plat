package io.github.kylinhunter.plat.core.security.filter;

import io.github.kylinhunter.commons.exception.common.KRuntimeException;
import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.core.security.service.TokenService;
import io.github.kylinhunter.plat.core.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.exception.WebErrInfoCustomizer;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import io.github.kylinhunter.plat.web.trace.TraceHandler;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 21:11
 */
public class JwtVerifyFilter extends BasicAuthenticationFilter {

  private TokenService tokenService;
  private TraceHandler traceHandler;
  private UserContextHandler userContextHandler;
  private ResponseWriter responseWriter;


  public JwtVerifyFilter(AuthenticationManager authenticationManager, TraceHandler traceHandler,
      TokenService tokenService, UserContextHandler userContextHandler,
      ResponseWriter responseWriter) {
    super(authenticationManager);
    this.traceHandler = traceHandler;
    this.tokenService = tokenService;
    this.userContextHandler = userContextHandler;
    this.responseWriter = responseWriter;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    try {
      traceHandler.create();

      String token = traceHandler.get().getToken();
      TokenUserDetails tokenUserDetails = null;
      try {
        tokenUserDetails = this.tokenService.verify(token);
      } catch (AuthException e) {
        logger.error("verify token error", e);
        responseWriter.write(e);
        return;
      }
      userContextHandler.create(tokenUserDetails.getToken());
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
          = new UsernamePasswordAuthenticationToken(tokenUserDetails, null,
          tokenUserDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

      chain.doFilter(request, response);

    } catch (KRuntimeException e) {
      throw new AuthenticationServiceException("verify token error" + e.getMessage(), e);

    } catch (Exception e) {
      throw new AuthenticationServiceException("verify token error", e);


    } finally {
      userContextHandler.remove();
      traceHandler.remove();
    }


  }

}