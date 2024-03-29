package io.github.kylinhunter.plat.storage.config;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import io.github.kylinhunter.commons.exception.embed.InitException;
import io.github.kylinhunter.plat.api.module.storage.constants.StorageType;

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
    private String a;
    private S3Config s3;
    private StorageType storageType;

    @PostConstruct
    private void init() {
        if(StringUtils.isEmpty(type)){
            throw new InitException("init storage config error,type is emtpy");
        }
        storageType = EnumUtils.fromName(StorageType.class, type.toUpperCase());
        log.info("init storage config ={}", this.toString());
    }

}
