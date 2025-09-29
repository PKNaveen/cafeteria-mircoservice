package com.cafeteria.user_service.config;

import com.cafeteria.user_service.Records.UserRecords;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootStrapServers;

//    ConsumerFactory strategy to start a consumer instance on any module
//    expectation is a JSON Object from kafka topic user-creation
@Bean
public ConsumerFactory<String, UserRecords> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "user-service-group");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    JsonDeserializer<UserRecords> valueDeserializer = new JsonDeserializer<>(UserRecords.class);
    valueDeserializer.addTrustedPackages("*");
    valueDeserializer.ignoreTypeHeaders();

    return new DefaultKafkaConsumerFactory<>(
            props,
            new StringDeserializer(),
            valueDeserializer
    );
}

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, UserRecords>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserRecords> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
