package com.cafeteria.cafeteriagateway.Domain;

import com.cafeteria.cafeteriagateway.Domain.Service.OrderService;
import com.cafeteria.cafeteriagateway.Domain.Service.UserService;
import com.cafeteria.cafeteriagateway.Record.OrderDetails;
import com.cafeteria.cafeteriagateway.Record.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messageQueue")
public class KafkaTopicController {

    private final UserService userService;
    private final OrderService orderService;

    public KafkaTopicController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    //    gets cart information from upstream in the following format
    //      {
    //      "userName": "TEXT",
    //      "userEmail": "TEXT",
    //      "imageUrl": "TEXT",
    //      "createdDate": "TEXT"
    //     }

    @PostMapping("/request/send-user-details")
    public String sendUserDetails(@RequestBody UserDetails userDetails) {
        userService.sendMessage(userDetails);
        return "Message posted to topic in kafka";
    }

    //    gets cart information from upstream in the following format
    //      {
    //        "cart_id":"UUID",
    //        "user_id":"UUID",
    //        "product_code":"TEXT",
    //        "quantity":"Long"
    //      }

    @PostMapping("/orders/creation-request")
    public String sendOrderCreationRequest(@RequestBody OrderDetails orderDetails) {
        orderService.sendOrder(orderDetails);
        return "Message posted to topic in kafka";
    }
}
