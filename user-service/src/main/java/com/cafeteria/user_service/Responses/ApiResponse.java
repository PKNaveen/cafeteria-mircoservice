package com.cafeteria.user_service.Responses;

import java.time.Instant;
import java.util.UUID;

public class ApiResponse {

    private final String transactionId;
    private final String systemTime;
    private final String message;
    private final String status;

    public ApiResponse(String message, String status) {
        this.transactionId = UUID.randomUUID().toString();
        this.systemTime = Instant.now().toString();
        this.message = message;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
