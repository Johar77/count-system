package com.johar.aggregatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.johar.aggregatorservice"})
public class AggregatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregatorServiceApplication.class, args);
    }

}
