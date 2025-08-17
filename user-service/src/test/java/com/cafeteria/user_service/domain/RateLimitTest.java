package com.cafeteria.user_service.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import com.cafeteria.user_service.Responses.ApiResponse;
import com.cafeteria.user_service.web.controllers.UserController;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class RateLimitTest {

    private UserController userController; // your controller
    private RateLimiter rateLimiter; // real or mocked
    private UserService userService; // mocked
    private Bucket bucket;
    private String validUuid;

    @BeforeEach
    void setup() {
        rateLimiter = org.mockito.Mockito.mock(RateLimiter.class);
        userService = org.mockito.Mockito.mock(UserService.class);
        userController = new UserController(userService, rateLimiter);

        // Create a real bucket with 5 tokens
        bucket = Bucket.builder()
                .addLimit(Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1))))
                .build();

        validUuid = UUID.randomUUID().toString();

        // Mock rateLimiter to always return the same bucket
        when(rateLimiter.resolveBucket(validUuid, 5)).thenReturn(bucket);

        // Mock service to always return empty → 404
        when(userService.findById(any(UUID.class))).thenReturn(Optional.empty());
    }

    @Test
    void shouldReturn404on6thRequestCall() {
        // First 5 calls -> 404
        for (int i = 0; i < 5; i++) {
            ResponseEntity<?> response = userController.findUserById(validUuid);
            assertEquals(404, response.getStatusCodeValue());
        }

        // 6th call -> 400
        ResponseEntity<?> response = userController.findUserById(validUuid);
        assertEquals(400, response.getStatusCodeValue());
        assertInstanceOf(ApiResponse.class, response.getBody());
    }
}
