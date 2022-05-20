package com.kylinhunter.plat.web.configuration;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kylinhunter.plat.commons.util.date.DatePatterns;
import com.kylinhunter.plat.web.i18n.CskbLocaleResolver;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-28 20:55
 **/

@Configuration
public class CommonConfiguration {

    @Bean // 将区域信息对象注册到容器
    public LocaleResolver localeResolver() {
        return new CskbLocaleResolver();
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
