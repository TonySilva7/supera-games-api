package com.supera.games.domain.services;

import com.supera.games.domain.model.Cart;
import com.supera.games.domain.model.Item;
import com.supera.games.domain.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

  public final CartRepository cartRepository;

  public CartService(CartRepository cartRepository) {
    this.cartRepository = cartRepository;
  }

  public Cart saveCart() {
    Cart myCart = new Cart();
    return cartRepository.save(myCart);
  }

  public List<Cart> findAllCarts() {
    return cartRepository.findAll();
  }

  public Optional<Cart> getCartById(Long id) {

    return cartRepository.findById(id);
  }
}
