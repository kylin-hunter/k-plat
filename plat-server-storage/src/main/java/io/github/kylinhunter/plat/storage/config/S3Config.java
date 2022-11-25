package io.github.kylinhunter.plat.storage.config;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 01:24
 **/
@Data
public class S3Config {
    private String bucket;
    private String url;
    private String accessKey;
    private String secretKey;
    private long connectTimeout = 500000;
    private long readTimeOut = 300000;
    private long partSize;

}
