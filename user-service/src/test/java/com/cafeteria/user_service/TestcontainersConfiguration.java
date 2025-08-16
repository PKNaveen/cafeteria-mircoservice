package com.cafeteria.user_service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:17-alpine"));
    }

    @Bean
    GenericContainer<?> redisContainer(ConfigurableEnvironment environment) {
        GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse("redis:8.2-alpine"))
                .withExposedPorts(6379)
                .waitingFor(Wait.forListeningPort());

        container.start();

        String address = "redis://" + container.getHost() + ":" + container.getMappedPort(6379);
        environment.getSystemProperties().put("redis.url", address);

        return container;
    }
}

//    @Bean
//    GenericContainer<?> redisContainer(ConfigurableEnvironment environment) {
//        GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse("redis:7-alpine"))
//                .withExposedPorts(6379)
//                .waitingFor(Wait.forListeningPort());
//
//        container.start();
//
//        String address = "redis://" + container.getHost() + ":" + container.getMappedPort(6379);
//        environment.getSystemProperties().put("redis.url", address);
//
//        return container;
//    }
