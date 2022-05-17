package com.kylinhunter.plat.web.interceptor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

/**
 * @description 拦截器
 * @author BiJi'an
 * @date   2021/7/29
 **/
@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final TraceHandlerInterceptor traceHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceHandlerInterceptor)
                .addPathPatterns(PathPatterns.include(PathPatterns.STORAGE_V1))
                .addPathPatterns(PathPatterns.include(PathPatterns.API_V1))
                .excludePathPatterns(PathPatterns.include(PathPatterns.SWAGGER))
                .excludePathPatterns(PathPatterns.include(PathPatterns.OPEN_API));


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}