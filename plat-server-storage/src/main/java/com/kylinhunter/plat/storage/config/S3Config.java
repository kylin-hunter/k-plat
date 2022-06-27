package com.kylinhunter.plat.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 01:24
 **/
@Data
@ConfigurationProperties(prefix = "kplat.storage.s3")
@Component
public class S3Config {
    private String url;
    private String accessKey;
    private String secretKey;
    private long callTimeOut = 60000;
    private long readTimeOut = 300000;
}
