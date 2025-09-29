package com.cafeteria.inventoryservice.Domain;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface InventoryRepo extends JpaRepository<InventoryEntity, UUID> {

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE inventory  SET stock_quantity = stock_quantity - :quantity "
                    + "where product_id = :product_id and stock_quantity >=1",
            nativeQuery = true)
    int updateStockQuantity(@Param("quantity") Long quantity, @Param("product_id") UUID product_id);
}
