package com.cqrs.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CqrsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CqrsApplication.class, args);
    }
}

