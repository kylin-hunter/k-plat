package io.github.kylinhunter.plat.web.feign;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.web.request.RequestConst;
import io.github.kylinhunter.plat.web.request.RequestContext;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-19 00:23
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class FeignTokenInterceptor implements RequestInterceptor {
    private final RequestContext requestContext;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        String feignToken = RequestConst.BEARER + requestContext.getToken();

        requestTemplate.header(RequestConst.HEADER_AUTH, feignToken);

    }


}
