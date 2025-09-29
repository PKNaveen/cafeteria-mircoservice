package com.cafeteria.cafeteriagateway.Domain.Service;

import com.cafeteria.cafeteriagateway.Record.UserDetails;
import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final KafkaTemplate<String, UserDetails> kafkaTemplate;

    public UserService(KafkaTemplate<String, UserDetails> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserDetails userDetails) {
        if (userDetails.getUserId() == null) {
            userDetails.setUserId(UUID.randomUUID());
        }

        kafkaTemplate.send("userCreation", userDetails);
    }
}
