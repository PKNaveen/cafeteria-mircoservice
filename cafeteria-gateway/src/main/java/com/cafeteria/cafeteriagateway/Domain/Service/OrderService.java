package com.cafeteria.cafeteriagateway.Domain.Service;

import com.cafeteria.cafeteriagateway.Record.OrderDetails;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final KafkaTemplate<String, OrderDetails> kafkaTemplate;

    public OrderService(KafkaTemplate<String, OrderDetails> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(OrderDetails orderDetails) {
        if (orderDetails.getCart_id() == null) {
            orderDetails.setCart_id(UUID.randomUUID());
        }
        kafkaTemplate.send("orderCreation", orderDetails);
    }
}
