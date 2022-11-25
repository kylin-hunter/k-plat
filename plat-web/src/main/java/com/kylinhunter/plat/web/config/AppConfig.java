package com.kylinhunter.plat.web.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.Env;

import io.github.kylinhunter.commons.util.EnumUtils;
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
@RefreshScope
public class AppConfig {
    @Value("${spring.application.name}")
    private String appName;
    @Value("${server.port}")
    private String serverPort;

    @Value("${kplat.devVersion:1.0}")
    private String devVersion;
    @Value("${kplat.productVersion:1.0}")
    private String productVersion;

    @Value("${kplat.threshold:0}")
    private int watchThreshold;

    @Value("${kplat.initialize.enabled:false}")
    private boolean initialize;

    @Value("${kplat.env:RELEASE}")
    private String envStr;
    private Env env;

    @PostConstruct
    private void init() {
        this.env = EnumUtils.fromName(Env.class, this.envStr);
        log.info("init config ok {}", this.toString());

    }

}
