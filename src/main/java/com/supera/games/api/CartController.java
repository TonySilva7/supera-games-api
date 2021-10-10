package com.supera.games.api;

import com.supera.games.domain.model.Cart;
import com.supera.games.domain.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping
  public ResponseEntity<List<Cart>> getCarts() {

    List<Cart> carts = cartService.findAllCarts();
    return ResponseEntity.ok().body(carts);
  }
}
