package com.cafeteria.inventoryservice.Domain;

import com.cafeteria.inventoryservice.Records.OrderDetails;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepo inventoryRepo;
    private final KafkaTemplate<String, String> kafkaTemplate;
    //    private final ObjectMapper mapper = new ObjectMapper();

    public InventoryService(InventoryRepo inventoryRepo, KafkaTemplate<String, String> kafkaTemplate) {
        this.inventoryRepo = inventoryRepo;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "orderCreation", groupId = "inventory-service-group")
    public void updateInventoryQuantity(OrderDetails orderDetails) {

        try {
            System.out.println("user_id:" + orderDetails.getUser_id());
            inventoryRepo.updateStockQuantity(
                    orderDetails.getQuantity(), UUID.fromString(orderDetails.getProduct_code()));
            kafkaTemplate.send("inventoryReserved", "message", "Inventory updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
