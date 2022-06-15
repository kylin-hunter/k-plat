package com.kylinhunter.plat.core.init;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.core.init.initializer.Initializer;
import com.kylinhunter.plat.web.config.AppConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-16 01:25
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class SystemDataInitializer {
    private final AppConfig appConfig;

    private final Map<String, Initializer> initializers;

    public boolean init(boolean force) {

        if (force || appConfig.isInitialize()) {
            TreeMap<Integer, Initializer> allIntializers = initializers.values().stream()
                    .collect(Collectors.toMap(e -> e.order(), e -> e, (o, n) -> {
                        throw new IllegalStateException(String.format("Duplicate key %s", o));
                    }, TreeMap::new));
            allIntializers.values().forEach(initializer -> {
                log.info("init order:" + initializer.order());
                initializer.init();
            });
            log.info("init ok");
        } else {
            log.info("skip init");
        }
        return true;
    }
}
