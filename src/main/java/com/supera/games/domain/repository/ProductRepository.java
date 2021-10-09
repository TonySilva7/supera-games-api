package com.supera.games.domain.repository;

import com.supera.games.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//  @Query(value = "SELECT * FROM Product p WHERE p.:type > 100 DESC")
//  List<Product> findProducts(@Param("type") String type);
}
