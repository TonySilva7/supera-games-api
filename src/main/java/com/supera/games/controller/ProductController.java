package com.supera.games.controller;

import com.sun.istack.NotNull;
import com.supera.games.model.Product;
import com.supera.games.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public @NotNull
    Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }
}
