package com.cafeteria.kafka_service;

import org.springframework.boot.SpringApplication;

public class TestKafkaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(KafkaServiceApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
