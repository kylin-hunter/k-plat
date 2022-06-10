package com.kylinhunter.plat.web.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.Env;
import com.kylinhunter.plat.commons.util.EnumUtil;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 主要配置
 * @author: BiJi'an
 * @create: 2021/7/14
 **/
@Component
@Data
@Slf4j
@ToString
public class WebConfig {
    @Value("${spring.application.name}")
    private String appName;
    @Value("${server.port}")
    private String serverPort;

    @Value("${app.devVersion:1.0}")
    private String devVersion;
    @Value("${app.productVersion:1.0}")
    private String productVersion;

    @Value("${apollo.meta}")
    private String apolloMeta;

    @Value("${app.mybatis.table_prefix}")
    private String tablePrefix;

    @Value("${app.threshold:0}")
    private int watchThreshold;

    @Value("${app.ngd.version}")
    private String ngdVersion;
    @Value("${app.redis.namespace:com.baidu.cskb::}")
    private String redisNS;

    @Value("${app.db.show-sql:false}")
    private boolean dbShowSql;

    @Value("${app.code:common}")
    private String appCode;

    @Value("${app.env:RELEASE}")
    private String envStr;
    private Env env;

    @Value("${app.audit.bpm}")
    private String bpmConfig;

    @PostConstruct
    private void init() {
        this.env = EnumUtil.fromName(Env.class, this.envStr);
        log.info("init config ok {}", this.toString());

    }

}
