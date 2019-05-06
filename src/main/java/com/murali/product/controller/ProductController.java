package com.murali.product.controller;

import com.murali.product.entity.Product;
import com.murali.product.repo.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public List<Product> getProducts() {
    	log.debug("Getting all the products..");
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
    	log.debug("Getting the products with id ["+ id +"]");
        Product product = productRepository.findById(id).orElse(new Product(-1L, "Not Available", "Product Not avilable", "-1", null));
        product.setSku("v2");
        return product;
    }

    @PostMapping("/product")
    public Product save(@RequestBody Product input) {
    	log.debug("Persisting the products details ["+ input +"]");
    	Product product =  productRepository.save(input);
    	product.setSku("v2");
        return product;
    }
}

