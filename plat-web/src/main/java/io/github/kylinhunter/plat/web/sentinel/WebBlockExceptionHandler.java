package io.github.kylinhunter.plat.web.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2023-09-28 17:07
 */
@Component
public class WebBlockExceptionHandler implements BlockExceptionHandler {


  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e)
      throws Exception {
    throw e;

  }
}