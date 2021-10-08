package com.supera.games.api;

import com.supera.games.domain.model.Product;
import com.supera.games.domain.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ProductController {

  private final ProductService prodService;

  public ProductController(ProductService prodService) {
    this.prodService = prodService;
  }

  @GetMapping("/products")
  public ResponseEntity<List<Product>> getProducts() {
    List<Product> products = prodService.getAllProducts();

    return ResponseEntity.ok().body(products);
  }

  @GetMapping("/products/{type}")
  public ResponseEntity<List<Product>> getProductsSorted(@PathVariable String type) {

    List<Product> products = prodService.getAllProductsSorted(type);

    return ResponseEntity.ok().body(products);
  }
}
