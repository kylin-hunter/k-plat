package com.kylinhunter.plat.storage.minio.client;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.httpclient.DefaultOkHttpClientFactory;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kylinhunter.plat.storage.config.S3Config;

import io.minio.MinioClient;
import okhttp3.OkHttpClient;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 01:25
 **/
@Configuration

public class MinioClientConfig {

    @Autowired
    private S3Config s3Config;

    @Bean
    public OkHttpClient httpClient() {
        OkHttpClientFactory okHttpClientFactory = new DefaultOkHttpClientFactory(new OkHttpClient().newBuilder());
        return okHttpClientFactory.createBuilder(true)
                .retryOnConnectionFailure(true)
                .callTimeout(s3Config.getCallTimeOut(), TimeUnit.MILLISECONDS)
                .readTimeout(s3Config.getReadTimeOut(), TimeUnit.MILLISECONDS)
                .build();
    }

    @Bean
    public MinioClient minioClient() {
        OkHttpClient client = httpClient();
        return MinioClient
                .builder().httpClient(client)
                .endpoint(s3Config.getUrl())
                .credentials(s3Config.getAccessKey(), s3Config.getSecretKey())
                .build();
    }
}
