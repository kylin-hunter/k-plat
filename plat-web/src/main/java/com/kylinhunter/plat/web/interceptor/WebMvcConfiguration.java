package com.kylinhunter.plat.web.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description 拦截器
 * @date 2022/01/01
 **/
@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final TraceHandlerInterceptor traceHandlerInterceptor;
    private final TokenHandlerInterceptor tokenHandlerInterceptor;
    private final TenantHandlerInterceptor tenantHandlerInterceptor;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceHandlerInterceptor)
                .addPathPatterns(PathPatterns.include(PathPatterns.API_V1))
                .excludePathPatterns(PathPatterns.include(PathPatterns.SWAGGER));

        registry.addInterceptor(tokenHandlerInterceptor)
                .addPathPatterns(PathPatterns.include(PathPatterns.API_V1))
                .addPathPatterns(PathPatterns.include(PathPatterns.AUTH));

        registry.addInterceptor(tenantHandlerInterceptor)
                .addPathPatterns(PathPatterns.include(PathPatterns.API_V1));

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}