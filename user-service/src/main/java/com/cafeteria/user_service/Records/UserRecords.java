package com.cafeteria.user_service.Records;

import java.util.UUID;

public record UserRecords(UUID userId, String userName, String userEmail, String imageUrl, String createdDate) {}
