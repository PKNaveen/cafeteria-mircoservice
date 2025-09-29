package com.cafeteria.cafeteriagateway;

import org.springframework.boot.SpringApplication;

public class TestCafeteriaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.from(CafeteriaGatewayApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
