package com.supera.games.domain.services;

import com.supera.games.domain.model.ShoppingCart;
import com.supera.games.domain.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

  private final ShoppingCartRepository cartRepository;

  public ShoppingCartService(ShoppingCartRepository cartRepository) {
    this.cartRepository = cartRepository;
  }

  public List<ShoppingCart> getCarts() {

    List<ShoppingCart> carts = cartRepository.findAll();

    return carts;

  }
}
