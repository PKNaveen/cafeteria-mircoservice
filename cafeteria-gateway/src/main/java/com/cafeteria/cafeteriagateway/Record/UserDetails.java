package com.cafeteria.cafeteriagateway.Record;

import java.util.UUID;

public class UserDetails {

    private UUID userId = UUID.randomUUID();
    private String userName;
    private String userEmail;
    private String imageUrl;
    private String createdDate;

    public UserDetails() {
        // default constructor for Jackson
    }

    public UserDetails(UUID transactionId, String userName, String userEmail, String imgUrl, String createdDate) {
        this.userId = transactionId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.imageUrl = imgUrl;
        this.createdDate = createdDate;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
