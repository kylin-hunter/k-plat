package com.kylinhunter.plat.data.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kylinhunter.plat.commons.exception.explain.ExplainCustomizer;
import com.kylinhunter.plat.data.exception.DataErrInfoCustomizer;
import com.kylinhunter.plat.data.exception.DataExplainCustomizer;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-07 00:06
 **/
@Configuration
public class DataComonConfiguration {

    @Bean
    public ExplainCustomizer daoExplainCustomizer() {
        return new DataExplainCustomizer();
    }

    @Bean
    public DataErrInfoCustomizer daoErrCustomizer() {
        return new DataErrInfoCustomizer();
    }
}
