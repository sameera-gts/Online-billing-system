package com.project.BillingProject.service;

import com.project.BillingProject.model.Product;
import com.project.BillingProject.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public List<Product> searchProducts(String query) {
        return repo.findByNameContainingIgnoreCase(query);
    }

    public Product getProductById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return repo.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setStockQty(updatedProduct.getStockQty());
            return repo.save(product);
        }).orElse(null);
    }

    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
}
