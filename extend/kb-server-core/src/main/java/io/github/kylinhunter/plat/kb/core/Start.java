package io.github.kylinhunter.plat.kb.core;

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
@SpringBootApplication(scanBasePackages = {"io.github.kylinhunter.plat"})
@MapperScan("io.github.kylinhunter.plat.kb.core.dao")
@EnableFeignClients
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
