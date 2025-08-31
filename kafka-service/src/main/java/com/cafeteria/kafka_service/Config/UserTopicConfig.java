package com.cafeteria.kafka_service.Config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserTopicConfig {

    @Bean
    public NewTopic userCreationTopic() {
        return new NewTopic("userCreation", 1, (short) 1);
    }

    @Bean
    public NewTopic userRoleChangeTopic() {
        return new NewTopic("userRoleChange", 1, (short) 1);
    }

    @Bean
    public NewTopic masterRoleTopic() {
        return new NewTopic("MasterRole", 1, (short) 1);
    }


}
