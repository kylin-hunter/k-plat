package com.kylinhunter.plat.storage.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.storage.constants.StorageType;

import io.github.kylinhunter.commons.util.EnumUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 01:24
 **/
@Data
@ConfigurationProperties(prefix = "kplat.storage")
@Component
@Slf4j
@ToString
public class StorageConfig {
    @Getter(AccessLevel.NONE)
    private String type;
    private S3Config s3;
    private StorageType storageType;

    @PostConstruct
    private void init() {
        storageType = EnumUtils.fromName(StorageType.class, type.toUpperCase());
        log.info("init storage config ={}", this.toString());
    }

}
