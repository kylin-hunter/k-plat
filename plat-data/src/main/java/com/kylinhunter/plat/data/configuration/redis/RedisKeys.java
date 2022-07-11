package com.kylinhunter.plat.data.configuration.redis;

import lombok.Setter;

/**
 * @description:
 * @author: BiJi'an
 * @create: 2022-03-15 16:16
 **/
public enum RedisKeys {
    AGENT_SEARCH_KEYWORD("search.keyword::"),
    AGENT_SEARCH_KEYWORD_TMP("search.keyword_tmp"),
    LICENSE_FLOW_CONTROLLER("flow.controller::");

    private String prefix;
    @Setter
    private String namespace = "com.kplat::";

    RedisKeys(String prefix) {
        this.prefix = prefix;
    }

    public String key(String name) {
        return namespace + prefix + name;
    }

    public String key() {
        return namespace + prefix;
    }

    public String prefix() {
        return namespace + prefix;
    }
}
