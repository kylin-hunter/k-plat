package org.jodconverter.sample.springboot;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-24 23:11
 **/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Main application. */
@SpringBootApplication
public class SpringBootWebApplication {

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}