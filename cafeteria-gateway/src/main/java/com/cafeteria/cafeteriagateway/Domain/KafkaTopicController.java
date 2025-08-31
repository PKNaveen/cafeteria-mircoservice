package com.cafeteria.cafeteriagateway.Domain;

import com.cafeteria.cafeteriagateway.Record.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaTopicController {

    private final UserService userService;

    public KafkaTopicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/send")
    public String send(@RequestBody UserDetails userDetails) {
        userService.sendMessage(userDetails);
        return "Message posted to topic in kafka";
    }
}
