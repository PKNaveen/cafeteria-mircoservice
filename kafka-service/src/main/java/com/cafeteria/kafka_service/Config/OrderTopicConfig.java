package com.cafeteria.kafka_service.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderTopicConfig {

    @Bean
    public NewTopic orderCreationTopic() {
        return new NewTopic("orderCreation", 1, (short) 1);
    }

    @Bean
    public NewTopic inventoryReservedTopic() {
        return new NewTopic("inventoryReserved", 1, (short) 1);
    }

    @Bean
    public NewTopic inventoryFailed() {
        return new NewTopic("inventoryFailed", 1, (short) 1);
    }

    @Bean
    public NewTopic paymentCompleted() {
        return new NewTopic("paymentCompleted", 1, (short) 1);
    }

    @Bean
    public NewTopic paymentFailed() {
        return new NewTopic("paymentFailed", 1, (short) 1);
    }


}
