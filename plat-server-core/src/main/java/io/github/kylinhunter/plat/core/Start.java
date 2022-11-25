package io.github.kylinhunter.plat.core;

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
@SpringBootApplication(scanBasePackages = {"io.github.kylinhunter.plat"})
@MapperScan("io.github.kylinhunter.plat.core.dao")
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
