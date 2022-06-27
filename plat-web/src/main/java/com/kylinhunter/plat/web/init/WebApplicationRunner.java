package com.kylinhunter.plat.web.init;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
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
@Order(value = 1)
public class WebApplicationRunner implements ApplicationRunner {

    private final ConfigurableEnvironment springEnv;

    @Override
    public void run(ApplicationArguments args) {

        MutablePropertySources propSrcs = springEnv.getPropertySources();
        // 获取所有配置
        Map<String, String> props = StreamSupport.stream(propSrcs.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), springEnv::getProperty));

        // key 和 value 之间的最小间隙
        int interval = 20;
        int max = props.keySet().stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("")
                .length();

        // 打印
        props.keySet().stream()
                .sorted()
                .forEach(k -> {
                    int i = max - k.length() + interval;
                    String join = String.join("", Collections.nCopies(i, " "));
                    System.out.println(String.format("%s%s%s", k, join, props.get(k)));
                });
    }
}
