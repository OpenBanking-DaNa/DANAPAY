package com.dana.danapay.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.dana.danapay")
@SpringBootApplication(scanBasePackages = "com.dana.danapay", exclude = SecurityAutoConfiguration.class)
//@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class DanapayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanapayApplication.class, args);
    }

}
