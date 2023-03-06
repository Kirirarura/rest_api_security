package com.epam.esm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of Spring Boot application.
 */
@SpringBootApplication(scanBasePackages = "com.epam.esm")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
