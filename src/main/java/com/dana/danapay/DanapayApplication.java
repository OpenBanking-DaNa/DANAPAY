package com.dana.danapay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.dana.danapay", exclude = SecurityAutoConfiguration.class)
public class DanapayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanapayApplication.class, args);
    }

}
