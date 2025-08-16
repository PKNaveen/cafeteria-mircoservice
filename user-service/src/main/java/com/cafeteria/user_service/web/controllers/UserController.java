package com.cafeteria.user_service.web.controllers;

import com.cafeteria.user_service.Responses.ApiResponse;
import com.cafeteria.user_service.domain.RateLimiter;
import com.cafeteria.user_service.domain.UserService;
import io.github.bucket4j.Bucket;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final RateLimiter rateLimiter;

    public UserController(UserService userService, RateLimiter rateLimiter) {
        this.userService = userService;
        this.rateLimiter = rateLimiter;
    }

    //    public String limitedEndpoint(@RequestParam(defaultValue = "user1") String key) {
    //        // Create bucket with 5 requests per minute
    //        Bucket bucket = rateLimiter.resolveBucket(key, 5);
    //
    //        if (bucket.tryConsume(1)) {
    //            return "✅ Request allowed for " + key;
    //        } else {
    //            return "❌ Too many requests. Try again later.";
    //        }
    //    }

    @GetMapping("/requests/{code}")
    public ResponseEntity<?> findUserById(@PathVariable("code") String key) {
        Bucket bucket = rateLimiter.resolveBucket(key, 5);

        try {
            UUID uuid = UUID.fromString(key);
            if (bucket.tryConsume(1)) {
                return userService.findById(uuid).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound()
                        .build());
            } else {
                ApiResponse apiResponse = new ApiResponse("Bad Request", "error");

                return ResponseEntity.badRequest().body(apiResponse);
            }
        } catch (IllegalArgumentException e) {
            ApiResponse apiResponse = new ApiResponse("Bad Request", "Invalid UUID");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
}
