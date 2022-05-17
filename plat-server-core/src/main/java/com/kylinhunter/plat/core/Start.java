package com.kylinhunter.plat.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author BiJi'an
 * @description
 * @date  2022-01-05 00:13
 **/
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.kylinhunter.plat"})
@MapperScan("com.kylinhunter.plat.dao")
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
