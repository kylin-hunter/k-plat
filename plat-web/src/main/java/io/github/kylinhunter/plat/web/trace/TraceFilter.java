package io.github.kylinhunter.plat.web.trace;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-12 19:35
 */
@WebFilter(filterName = "TraceFilter", urlPatterns = "/*")
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class TraceFilter implements Filter {

  private final TraceHolder traceHolder;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;

    try {
      traceHolder.create(request);
      chain.doFilter(servletRequest, servletResponse);
    } finally {
      traceHolder.remove();
    }

  }

  @Override
  public void destroy() {
  }
}