package com.kylinhunter.plat.ex.knowledge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author BiJi'an
 * @description
 * @date  2022-01-01 00:13
 **/
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.kylinhunter.plat"})
@MapperScan("com.kylinhunter.plat.dao")
@EnableFeignClients
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
