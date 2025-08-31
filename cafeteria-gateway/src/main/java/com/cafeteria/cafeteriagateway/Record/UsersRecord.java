package com.cafeteria.cafeteriagateway.Record;

import java.util.UUID;

public record UsersRecord(
        UUID transactionId,
        String userName,
        String userEmail,
        String imgUrl,
        String createdDate
) {}
