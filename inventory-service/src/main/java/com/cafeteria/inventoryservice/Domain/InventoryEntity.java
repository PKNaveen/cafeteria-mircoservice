package com.cafeteria.inventoryservice.Domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "inventory")
public class InventoryEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID inventoryId;

    @Column(nullable = false, unique = true)
    private String productId;

    @Column(nullable = false, unique = true)
    private Long stockQuantity;

    @CreationTimestamp
    @Column(name = "last_update_date", updatable = true, nullable = false)
    private LocalDateTime updateDate;

    public InventoryEntity(UUID inventoryId, String productId, Long stockQuantity, LocalDateTime updateDate) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.stockQuantity = stockQuantity;
        this.updateDate = updateDate;
    }

    public InventoryEntity() {}

    public UUID getInventoryId() {
        return inventoryId;
    }

    public String getProductId() {
        return productId;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setInventoryId(UUID inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
