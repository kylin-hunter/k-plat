package io.github.kylinhunter.plat.storage.minio.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.commons.httpclient.DefaultOkHttpClientFactory;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.kylinhunter.plat.storage.config.S3Config;
import io.github.kylinhunter.plat.storage.config.StorageConfig;

import io.minio.MinioClient;
import okhttp3.OkHttpClient;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 01:25
 **/
@Configuration
@ConditionalOnExpression("'${kplat.storage.type}' == 'minio'")
public class MinioConfiguration {

    @Autowired
    private StorageConfig storageConfig;

    @Bean
    public OkHttpClient httpClient() {
        final S3Config s3Config = storageConfig.getS3();
        OkHttpClientFactory okHttpClientFactory = new DefaultOkHttpClientFactory(new OkHttpClient().newBuilder());
        return okHttpClientFactory.createBuilder(true)
                .retryOnConnectionFailure(true)
                .connectTimeout(s3Config.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(s3Config.getReadTimeOut(), TimeUnit.MILLISECONDS)
                .build();
    }

    @Bean
    public MinioClient minioClient() {
        final S3Config s3Config = storageConfig.getS3();
        OkHttpClient client = httpClient();
        return MinioClient
                .builder().httpClient(client)
                .endpoint(s3Config.getUrl())
                .credentials(s3Config.getAccessKey(), s3Config.getSecretKey())
                .build();
    }
}
