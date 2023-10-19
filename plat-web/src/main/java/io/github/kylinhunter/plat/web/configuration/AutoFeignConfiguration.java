/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.web.configuration;

import feign.codec.ErrorDecoder;
import io.github.kylinhunter.plat.web.exception.WebErrInfos;
import io.github.kylinhunter.plat.web.exception.WebException;
import io.github.kylinhunter.plat.web.feign.FeignComponent;
import io.github.kylinhunter.plat.web.feign.FeignTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BiJi'an
 * @description
 * @date 2023-09-25 19:08
 */
@Configuration
@Slf4j
public class AutoFeignConfiguration {

  protected ErrorDecoder errorDecoder = new ErrorDecoder.Default();

  //  @Bean
  //  public Retryer retryer() {
  // return Retryer.NEVER_RETRY;
  // 最大请求次数为5，初始间隔时间为100ms，下次间隔时间1.5倍递增，重试间最大间隔时间为1s，
  //    return Retryer.Default;
  //  }

  @Bean
  public ErrorDecoder errorDecoder(FeignComponent feignComponent) {
    return (key, response) -> {
      String msg = feignComponent.getResponseBody(response);
      int status = response.status();
      if (status >= 400 && status <= 499) {
        return new WebException(
            WebErrInfos.FEIGN_REQUEST_ERROR, "(" + status + ")" + key + ":" + msg);
      }
      if (status >= 500 && status <= 599) {
        return new WebException(
            WebErrInfos.FEIGN_SERVER_ERROR, "(" + status + ") =>" + key + ":" + msg);
      }
      return new WebException(
          WebErrInfos.FEIGN_ERROR, "(" + status + ")=>" + key + ":" + msg);

      //      return errorDecoder.decode(key, response);
    };
  }

  @Bean
  public FeignComponent foodComponent() {
    return new FeignComponent();
  }

  @Bean
  public FeignTokenInterceptor feignTokenInterceptor() {
    return new FeignTokenInterceptor();
  }
}
