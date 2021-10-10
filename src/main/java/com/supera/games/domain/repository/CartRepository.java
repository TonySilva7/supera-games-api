package com.supera.games.domain.repository;

import com.supera.games.domain.model.Cart;
import com.supera.games.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
