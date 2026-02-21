package com.project.BillingProject.repo;

import com.project.BillingProject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Case-insensitive search by name
    List<Product> findByNameContainingIgnoreCase(String name);
}
