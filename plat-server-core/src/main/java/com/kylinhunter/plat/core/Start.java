package com.kylinhunter.plat.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author BiJi'an
 * @description
 * @date  2022-01-01 00:13
 **/
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.kylinhunter.plat"})
@MapperScan("com.kylinhunter.plat.core.dao")
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
