package com.supera.games.service;

import com.supera.games.model.Cart;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface CartService {

  @NotNull Iterable<Cart> getAllCarts();

  Cart create(@NotNull(message = "The cart cannot be null.") @Valid Cart cart);

  void update(@NotNull(message = "The cart cannot be null.") @Valid Cart cart);
}
