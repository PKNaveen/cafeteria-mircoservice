package com.cafeteria.inventoryservice.Domain;

import com.cafeteria.inventoryservice.Records.OrderRecords;
import java.util.UUID;

class InventoryMapper {

    public static InventoryEntity ToInventoryEntity(final OrderRecords orderRecords) {
        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setInventoryId(UUID.randomUUID());
        inventoryEntity.setProductId(orderRecords.productId());
        inventoryEntity.setUpdateDate(java.time.LocalDateTime.now());
        return inventoryEntity;
    }
}
