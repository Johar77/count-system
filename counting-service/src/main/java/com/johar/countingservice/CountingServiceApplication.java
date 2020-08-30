package com.johar.countingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.johar.commonlib", "com.johar.countingservice"})
public class CountingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountingServiceApplication.class, args);
    }

}
