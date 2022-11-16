package com.kylinhunter.plat.web.error;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BiJi'an
 * @description
 * @date 2022-11-17 02:13
 **/
@Configuration
public class ErrorConfiguration {

    @Bean
    public ErrorMessageController basicErrorController(ErrorAttributes errorAttributes,
                                                       ServerProperties serverProperties,
                                                       ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {

        return new ErrorMessageController(errorAttributes, serverProperties.getError(),
                errorViewResolversProvider.getIfAvailable());
    }
}
