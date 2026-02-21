package com.project.BillingProject.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long orderId;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private List<OrderItemDto> items;

    // Getters & Setters
}
