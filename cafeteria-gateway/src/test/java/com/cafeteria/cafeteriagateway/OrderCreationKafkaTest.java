package com.cafeteria.cafeteriagateway;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TestcontainersConfiguration.class)
public class OrderCreationKafkaTest {
    @Test
    void testOrderCreationTopic() {
        String topic = "orderCreation";
        String key = "order1";
        String value = "{\"orderId\":123,\"status\":\"CREATED\"}";

        // Producer properties
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, TestcontainersConfiguration.getBootstrapServers());
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps)) {
            producer.send(new ProducerRecord<>(topic, key, value));
            producer.flush();
        }

        // Consumer properties
        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, TestcontainersConfiguration.getBootstrapServers());
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps)) {
            consumer.subscribe(Collections.singletonList(topic));
            ConsumerRecord<String, String> record = null;
            long end = System.currentTimeMillis() + 10000; // 10 seconds timeout
            while (System.currentTimeMillis() < end) {
                var records = consumer.poll(Duration.ofMillis(500));
                if (!records.isEmpty()) {
                    record = records.iterator().next();
                    break;
                }
            }
            assertEquals(key, record.key());
            assertEquals(value, record.value());
        }
    }
}
