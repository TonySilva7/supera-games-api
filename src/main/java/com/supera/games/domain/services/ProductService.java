package com.supera.games.domain.services;

import com.supera.games.domain.model.Product;
import com.supera.games.domain.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository prodRepo;

  public ProductService(ProductRepository prodRepo) {
    this.prodRepo = prodRepo;
  }


  public List<Product> getAllProducts() {
    List<Product> products = prodRepo.findAll();
    return products;
  }

  public Optional<Product> getProductById(Long id) {
    return prodRepo.findById(id);
  }

  public List<Product> getAllProductsSorted(String type) {
    Sort sort = type.equals("score") ? Sort.by(type).descending() : Sort.by(type).ascending();
    List<Product> products = prodRepo.findAll(sort);
    return products;
  }
}
