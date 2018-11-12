package com.bootu.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bootu.*"})
public class BootuSecurityDemo {
    public static void main(String[] args) {
        SpringApplication.run(BootuSecurityDemo.class);
    }
}
