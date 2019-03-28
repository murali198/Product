package com.murali.product.controller;

import com.murali.product.entity.Product;
import com.murali.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping("/product")
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }
}

