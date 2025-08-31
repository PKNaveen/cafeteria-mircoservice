package com.cafeteria.cafeteriagateway.Record;

import java.util.UUID;

public class UserDetails {

    private UUID transactionId = UUID.randomUUID();
    private String userName;
    private String userEmail;
    private String imgUrl;
    private String createdDate;

    public UserDetails() {
        // default constructor for Jackson
    }

    public UserDetails(UUID transactionId, String userName, String userEmail, String imgUrl, String createdDate) {
        this.transactionId = transactionId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.imgUrl = imgUrl;
        this.createdDate = createdDate;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
