package com.project.BillingProject.controller;

import com.project.BillingProject.dto.OrderRequestDto;
import com.project.BillingProject.model.Order;
import com.project.BillingProject.model.OrderItem;
import com.project.BillingProject.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody List<OrderItem> items) {
        return service.createOrder(items);
    }
}
