package com.kylinhunter.plat.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date  2022-01-04 17:35
 **/
@EnableEurekaServer
@SpringBootApplication
@Slf4j
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
