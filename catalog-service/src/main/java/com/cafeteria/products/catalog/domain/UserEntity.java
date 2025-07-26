package com.cafeteria.products.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users")
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(nullable = false)
    @NotBlank(message = "User name cannot be null")
    private String userName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "User email cannot be empty")
    private String userEmail;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "User img url cannot be null")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    public UserEntity(String id, String userName, String userEmail, String imageUrl, LocalDateTime createdDate) {
        this.userId = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
    }

    public UserEntity() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
