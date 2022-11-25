package io.github.kylinhunter.plat.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author BiJi'an
 * @description
 * @date  2022-01-01 00:13
 **/
@SpringBootApplication(scanBasePackages = {"io.github.kylinhunter.plat"})
@MapperScan("io.github.kylinhunter.plat.data")
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
