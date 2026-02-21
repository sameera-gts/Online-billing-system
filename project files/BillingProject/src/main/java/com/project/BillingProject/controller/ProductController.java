package com.project.BillingProject.controller;

import com.project.BillingProject.model.Product;
import com.project.BillingProject.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // DEV: allow any origin. For production restrict origins.
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET /api/products
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    // GET /api/products/search?q=term
    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Product>> search(@RequestParam(name = "q", required = false) String query) {
        if (query == null || query.isBlank()) {
            return ResponseEntity.ok(service.getAllProducts());
        }
        return ResponseEntity.ok(service.searchProducts(query));
    }

    // GET /api/products/{id}
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product p = service.getProductById(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    // POST /api/products
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product saved = service.saveProduct(product);
        return ResponseEntity.ok(saved);
    }

    // PUT /api/products/{id}
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Product updated = service.updateProduct(id, product);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
