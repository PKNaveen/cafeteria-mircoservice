package com.cafeteria.cafeteriagateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class TestcontainersConfiguration {
    private static final KafkaContainer kafkaContainer;

    static {
        kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));
        kafkaContainer.start();
    }

    @Bean(destroyMethod = "stop")
    public KafkaContainer kafkaContainer() {
        return kafkaContainer;
    }

    public static String getBootstrapServers() {
        return kafkaContainer.getBootstrapServers();
    }
}
