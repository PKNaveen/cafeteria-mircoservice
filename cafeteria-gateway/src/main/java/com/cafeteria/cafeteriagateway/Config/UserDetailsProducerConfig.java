package com.cafeteria.cafeteriagateway.Config;

import com.cafeteria.cafeteriagateway.Record.UserDetails;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class UserDetailsProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    //    ProducerFactory uses a key, value Hashmap to convert the data into byte arrays, byte arrays is the only method
    // kafka understands to
    //    move messages. This is the same when JSON object is converted via serializers and through utf-8 byte arrays as
    // below
    @Bean
    public ProducerFactory<String, UserDetails> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, UserDetails> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
