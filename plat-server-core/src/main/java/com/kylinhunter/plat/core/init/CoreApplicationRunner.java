package com.kylinhunter.plat.core.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:51
 **/
@Slf4j
@Component
@RequiredArgsConstructor
@Order(value = 3)
public class CoreApplicationRunner implements ApplicationRunner {

    private final SystemDataInitializer systemDataInitializer;

    @Override
    public void run(ApplicationArguments args) {

        systemDataInitializer.init(false);
    }
}
