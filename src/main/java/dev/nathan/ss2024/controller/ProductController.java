package dev.nathan.ss2024.controller;

import dev.nathan.ss2024.entity.Product;
import dev.nathan.ss2024.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepo productRepo;

    @GetMapping("/public/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepo.findAll());
    }

    @PostMapping("/admin/saveproduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productRepo.save(product));
    }

    @GetMapping("/user/alone")
    public ResponseEntity<String> userAlone() {
        return ResponseEntity.ok("Users alone can access this api only");
    }

    @GetMapping("/adminuser/both")
    public ResponseEntity<String> adminUserBoth() {
        return ResponseEntity.ok("Both admin and users can access this api");
    }

}
