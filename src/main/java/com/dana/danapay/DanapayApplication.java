package com.dana.danapay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.dana.danapay")
public class DanapayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanapayApplication.class, args);
    }

}
