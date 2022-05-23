package com.kylinhunter.plat.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author BiJi'an
 * @description
 * @date  2022-01-01 00:13
 **/
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.kylinhunter.plat"})
@MapperScan("com.kylinhunter.plat.dao")
@EnableFeignClients
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
