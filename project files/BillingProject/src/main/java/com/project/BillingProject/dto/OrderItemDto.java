package com.project.BillingProject.dto;

import java.math.BigDecimal;

public class OrderItemDto {
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;  // optional if backend calculates

    // Getters & Setters
}
