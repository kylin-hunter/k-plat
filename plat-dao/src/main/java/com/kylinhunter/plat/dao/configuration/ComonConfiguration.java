package com.kylinhunter.plat.dao.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kylinhunter.plat.commons.exception.explain.ExplainCustomizer;
import com.kylinhunter.plat.dao.exception.DaoErrInfoCustomizer;
import com.kylinhunter.plat.dao.exception.DaoExplainCustomizer;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-07 00:06
 **/
@Configuration
public class ComonConfiguration {

    @Bean
    public ExplainCustomizer exceptionExplainCustomizer() {
        return new DaoExplainCustomizer();
    }

    @Bean
    public DaoErrInfoCustomizer daoErrCustomizer() {
        return new DaoErrInfoCustomizer();
    }
}
