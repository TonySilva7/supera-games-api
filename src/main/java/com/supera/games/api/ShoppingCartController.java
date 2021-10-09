package com.supera.games.api;

import com.supera.games.domain.model.Product;
import com.supera.games.domain.model.ShoppingCart;
import com.supera.games.domain.services.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ShoppingCartController {

  private final ShoppingCartService cartService;

  public ShoppingCartController(ShoppingCartService prodService, ShoppingCartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/carts")
  public ResponseEntity<List<ShoppingCart>> getProducts() {
    List<ShoppingCart> carts = cartService.getCarts();
    return ResponseEntity.ok().body(carts);
  }

}
