package io.github.kylinhunter.plat.web.configuration;

import feign.codec.ErrorDecoder;
import io.github.kylinhunter.plat.web.exception.WebErrInfoCustomizer;
import io.github.kylinhunter.plat.web.exception.WebException;
import io.github.kylinhunter.plat.web.feign.FeignComponent;
import io.github.kylinhunter.plat.web.feign.FeignTokenInterceptor;
import io.github.kylinhunter.plat.web.request.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BiJi'an
 * @description
 * @date 2023-09-25 19:08
 */
@Configuration
@Slf4j
public class FeignConfiguration {

  protected ErrorDecoder errorDecoder = new ErrorDecoder.Default();


//  @Bean
//  public Retryer retryer() {
  //return Retryer.NEVER_RETRY;
  //最大请求次数为5，初始间隔时间为100ms，下次间隔时间1.5倍递增，重试间最大间隔时间为1s，
  //    return Retryer.Default;
//  }

  @Bean
  public ErrorDecoder errorDecoder(FeignComponent feignComponent) {
    return (key, response) -> {
      String msg = feignComponent.getResponseBody(response);
      int status = response.status();
      if (status >= 400 && status <= 499) {
        return new WebException(WebErrInfoCustomizer.FEIGN_REQUEST_ERROR,
            "(" + status + ")" + key + ":" + msg);

      }
      if (status >= 500 && status <= 599) {
        return new WebException(WebErrInfoCustomizer.FEIGN_SERVER_ERROR,
            "(" + status + ") =>" + key + ":" + msg);

      }
      return new WebException(WebErrInfoCustomizer.FEIGN_ERROR,
          "(" + status + ")=>" + key + ":" + msg);

//      return errorDecoder.decode(key, response);
    };


  }

  @Bean
  public FeignComponent foodComponent() {
    return new FeignComponent();
  }

  @Bean
  public FeignTokenInterceptor feignTokenInterceptor(RequestContext requestContext) {
    return new FeignTokenInterceptor(requestContext);
  }
}