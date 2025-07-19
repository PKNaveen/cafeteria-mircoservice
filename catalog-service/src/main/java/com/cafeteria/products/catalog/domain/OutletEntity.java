package com.cafeteria.products.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "outlets")
class OutletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String shopId;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Shop Name cannot be blank")
    private String shopName;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Shop Location cannot be blank")
    private String shopLocation;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Shop rating cannot be blank")
    private String shopRating;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    public OutletEntity(
            String shopId, String shopName, String shopLocation, String shopRating, LocalDateTime createdDate) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.shopRating = shopRating;
        this.createdDate = createdDate;
    }

    public OutletEntity() {}

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public String getShopRating() {
        return shopRating;
    }

    public void setShopRating(String shopRating) {
        this.shopRating = shopRating;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
