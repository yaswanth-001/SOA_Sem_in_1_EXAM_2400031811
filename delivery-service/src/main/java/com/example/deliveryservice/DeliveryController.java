package com.example.deliveryservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {

    // GET /orders
    @GetMapping("/orders")
    public String getOrders() {
        return "yaswanth welcome!";
    }

    // POST /adduser
    @PostMapping("/adduser")
    public String addUser(@RequestBody Delivery delivery) {
        return "User added successfully: " + delivery.getName();
    }
}
