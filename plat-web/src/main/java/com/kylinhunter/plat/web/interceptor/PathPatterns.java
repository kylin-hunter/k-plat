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

    public static final PathPattern API_V1 = PathPattern.builder().build()
            .add("/api/v1/**");

    public static final PathPattern API_V1_TOKEN = PathPattern.builder().build()
            .add("/api/v1/auth/token/**");

    public static final PathPattern API_V1_CORE_AGENT = PathPattern.builder().build()
            .add("/api/v1/core/agent/**");

    public static final PathPattern API_V1_CORE_SETTINGS_INDUSTRY = PathPattern.builder().build()
            .add("/api/v1/core/settings_industry/**");

    public static final PathPattern API_V1_CORE_USER_SETTINGS = PathPattern.builder().build()
            .add("/api/v1/core/user_settings/**");

    public static final PathPattern API_V1_CORE_SYS_SETTINGS = PathPattern.builder().build()
            .add("/api/v1/core/sys_settings/**");
    public static final PathPattern API_V1_CORE_DICTIONARY = PathPattern.builder().build()
            .add("/api/v1/core/dictionary/**");

    public static final PathPattern API_V1_CORE_USER_CONFIG = PathPattern.builder().build()
            .add("/api/v1/core/user_config/**");

    public static final PathPattern API_V1_CORE_SYSTEM_MODULE = PathPattern.builder().build()
            .add("/api/v1/core/system_module/**");

    public static final PathPattern API_V1_CORE_DICTIONARY_GROP = PathPattern.builder().build()
            .add("/api/v1/core/dictionary_group/**");

    public static final PathPattern STORAGE_V1 = PathPattern.builder().build()
            .add("/storage/v1/**");

    public static final PathPattern OPEN_API_V1 = PathPattern.builder().build().
            add("/open/api/v1/*");
    public static final PathPattern OPEN_API = PathPattern.builder().build().
            add("/open/api/**");

    public static final PathPattern INNER_API_V1 = PathPattern.builder().build().
            add("/inner/api/v1/*");

    public static final PathPattern TASK_CENTER_V1 = PathPattern.builder().build().
            add("/api/v1/task_center").
            add("/api/v1/task_center/retry").
            add("/api/v1/task_center/cancel").
            add("/api/v1/task_center/delete").
            add("/api/v1/task_center/deleteById");

    public static final PathPattern FAQ_MINING_CALLBACK_V1 = PathPattern.builder().build().
            add("/api/v1/faq/faq_mining_task/callback");

    public static final PathPattern MESSAGE_API_V1 = PathPattern.builder().build().
            add("/api/v1/tc/message/**");

    public static final PathPattern LOG_API_V1 = PathPattern.builder().build().
            add("/inner/api/v1/test/**")
            .add("/api/v1/core/agent/create")
            .add("/api/v1/core/agent/update")
            .add("/api/v1/core/agent/delete")
            .add("/api/v1/core/directory/create")
            .add("/api/v1/core/directory/update")
            .add("/api/v1/core/directory/delete")
            .add("/api/v1/core/bpm_config/bind")
            .add("/api/v1/portal/comment/audit")
            .add("/api/v1/task_center/create")
            .add("/api/v1/task_center/deleteById")
            .add("/api/v1/core/auth_user/create")
            .add("/api/v1/core/auth_user/update")
            .add("/api/v1/core/auth_user/delete")
            .add("/api/v1/core/auth_role/create")
            .add("/api/v1/core/auth_role/update")
            .add("/api/v1/core/auth_role/delete")
            .add("/api/v1/core/channel_dimension/create")
            .add("/api/v1/core/channel_dimension/update")
            .add("/api/v1/core/channel_dimension/delete")
            .add("/api/v1/core/channel_dimension/priority")
            .add("/api/v1/core/channel_dimension_value/create")
            .add("/api/v1/core/channel_dimension_value/update")
            .add("/api/v1/core/channel_dimension_value/delete")
            .add("/api/v1/core/channel_special_value/create")
            .add("/api/v1/core/channel_special_value/update")
            .add("/api/v1/core/channel_special_value/delete");

    public static final PathPattern LOG_OPEN_API = PathPattern.builder().build()
            .add("/open/api/**/create")
            .add("/open/api/**/update")
            .add("/open/api/**/delete")
            .add("/open/api/**/move");

    public static final PathPattern SWAGGER = PathPattern.builder().build()
            .add("/swagger-resources/**")
            .add("/webjars/**")
            .add("/v2/**")
            .add("/swagger-ui.html/**");
    public static final PathPattern TENANT_LIST = PathPattern.builder().build()
            .add("/api/v1/audit/tenant/body");

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
        Set<String> paths = Sets.newHashSet();

        public PathPattern add(String path) {
            paths.add(path);
            return this;
        }

    }

}

