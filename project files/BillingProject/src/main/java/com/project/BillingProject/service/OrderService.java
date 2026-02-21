package com.project.BillingProject.service;

import com.project.BillingProject.model.Order;
import com.project.BillingProject.model.OrderItem;
import com.project.BillingProject.model.Product;
import com.project.BillingProject.repo.OrderRepository;
import com.project.BillingProject.repo.ProductRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    public Order createOrder(List<OrderItem> items) {
        Order order = new Order();
        BigDecimal total = BigDecimal.ZERO;

        for(OrderItem item : items) {
            Product product = productRepo.findById(item.getProduct().getId()).orElseThrow();
            item.setUnitPrice(product.getPrice());
            item.setLineTotal(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            item.setOrder(order);
            order.addItem(item);

            total = total.add(item.getLineTotal());
        }

        order.setTotalAmount(total);
        return orderRepo.save(order);
    }
}
