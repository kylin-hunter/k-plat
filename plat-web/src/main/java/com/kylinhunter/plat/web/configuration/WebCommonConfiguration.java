package com.kylinhunter.plat.web.configuration;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kylinhunter.plat.web.i18n.KplatLocaleResolver;

import io.github.kylinhunter.commons.date.DatePatterns;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 20:55
 **/

@Configuration
public class WebCommonConfiguration {

    @Bean // 将区域信息对象注册到容器
    public LocaleResolver localeResolver() {
        return new KplatLocaleResolver();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.ALWAYS);
            jacksonObjectMapperBuilder.failOnUnknownProperties(false);
            //                jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            LocalDateTimeSerializer localDateTimeSerializer =
                    new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePatterns.DATE_TIME));

            LocalDateTimeDeserializer localDateTimeDeserializer =
                    new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePatterns.DATE_TIME));

            jacksonObjectMapperBuilder.serializers(localDateTimeSerializer);
            jacksonObjectMapperBuilder.deserializers(localDateTimeDeserializer);

        };
    }

}
