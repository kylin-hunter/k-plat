package com.kylinhunter.plat.web.interceptor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import lombok.Builder;
import lombok.Data;

/**
 * @description 预定义拦截路径若干
 * @author BiJi'an
 * @date   2022-01-01 16:59
 **/
public class PathPatterns {
    public static final PathPattern LOGIN = PathPattern.builder().build()
            .add("/login");
    public static final PathPattern AUTH = PathPattern.builder().build()
            .add("/auth/**");

    public static final PathPattern API_V1 = PathPattern.builder().build()
            .add("/api/v1/**");

    public static final PathPattern SWAGGER = PathPattern.builder().build()
            .add("/swagger-resources/**")
            .add("/webjars/**")
            .add("/v2/**")
            .add("/swagger-ui.html/**");


    public static String[] include(PathPattern... patterns) {
        if (patterns.length <= 0) {
            return new String[0];
        } else if (patterns.length == 1) {
            return patterns[0].paths.toArray(new String[0]);
        } else {
            return Arrays.stream(patterns).map(e -> {
                return e.paths.toArray(new String[0]);
            }).flatMap(es -> {
                return Arrays.stream(es);
            }).distinct().collect(Collectors.toList()).toArray(new String[0]);
        }

    }

    @Data
    @Builder
    public static class PathPattern {
        @Builder.Default
        private Set<String> paths = Sets.newHashSet();

        public PathPattern add(String path) {
            paths.add(path);
            return this;
        }

    }

}

