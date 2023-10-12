package io.github.kylinhunter.plat.web.security.filter;

import io.github.kylinhunter.commons.lang.strings.StringUtil;
import io.github.kylinhunter.plat.web.exception.AuthException;
import io.github.kylinhunter.plat.web.exception.WebErrInfoCustomizer;
import io.github.kylinhunter.plat.web.response.DefaultResponse;
import io.github.kylinhunter.plat.web.response.ResponseWriter;
import io.github.kylinhunter.plat.web.security.bean.TokenUserDetails;
import io.github.kylinhunter.plat.web.security.service.TokenService;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 21:10
 */
@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  private final ResponseWriter responseWriter;
  private final TraceHolder traceHolder;


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    if (!request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    }
    String username = StringUtil.defaultString(obtainUsername(request)).trim();
    String password = StringUtil.defaultString(obtainPassword(request)).trim();
    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
        username, password);
    // Allow subclasses to set the "details" property
    setDetails(request, authRequest);
    return this.authenticationManager.authenticate(authRequest);
  }

  /**
   * 一旦调用 springSecurity认证登录成功，立即执行该方法
   */
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) {

    try {
      TokenUserDetails userDetail = (TokenUserDetails) authResult.getPrincipal();
      String tetantId = request.getParameter("tenantId");
      userDetail.setTenantId(tetantId);
      String token = tokenService.createToken(userDetail);
      DefaultResponse<String> rep = new DefaultResponse<>(token);
      rep.setTrace(traceHolder.get());
      responseWriter.writeJson(rep);
    } catch (AuthException e) {
      throw new AuthenticationServiceException(e.getMessage(), e);
    } catch (Exception e) {
      throw new AuthenticationServiceException("craate token error", e);

    }
  }


  /**
   * 一旦调用 springSecurity认证失败 ，立即执行该方法
   */
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException ex) {

    responseWriter.writeErr(WebErrInfoCustomizer.AUTH_ERROR, "auth error:" + ex.getMessage(),"");
  }
}