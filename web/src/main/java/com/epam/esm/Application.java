package com.epam.esm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The entry point of Spring Boot application.
 */
@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.epam.esm")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
