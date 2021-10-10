package com.supera.games.service;

import com.supera.games.model.Cart;
import com.supera.games.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class CartServiceImpl implements CartService {

  private CartRepository cartRepository;

  public CartServiceImpl(CartRepository cartRepository) {
    this.cartRepository = cartRepository;
  }

  @Override
  public Iterable<Cart> getAllCarts() {
    return this.cartRepository.findAll();
  }

  @Override
  public Cart create(Cart cart) {
    cart.setDateCreated(LocalDate.now());

    return this.cartRepository.save(cart);
  }

  @Override
  public void update(Cart cart) {
    this.cartRepository.save(cart);
  }
}
