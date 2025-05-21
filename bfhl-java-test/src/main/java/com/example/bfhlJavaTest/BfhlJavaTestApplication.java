package com.example.bfhlJavaTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BfhlJavaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BfhlJavaTestApplication.class, args);
    }

}