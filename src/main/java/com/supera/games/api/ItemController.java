package com.supera.games.api;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import com.supera.games.domain.model.Item;
import com.supera.games.domain.model.Product;
import com.supera.games.domain.model.ShoppingCart;
import com.supera.games.domain.repository.ItemRepository;
import com.supera.games.domain.repository.ProductRepository;
import com.supera.games.domain.repository.ShoppingCartRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemController {

  private final ProductRepository productRepository;
  private final ItemRepository itemRepository;
  private final ShoppingCartRepository cartRepository;

  public ItemController(ProductRepository productRepository, ItemRepository itemRepository, ShoppingCartRepository cartRepository) {
    this.productRepository = productRepository;
    this.itemRepository = itemRepository;
    this.cartRepository = cartRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ShoppingCart> saveItem(@RequestBody Item item) {

    // to sava an item, we need to know the product
    Optional<Product> product = productRepository.findById(item.getProduct().getId());


      // if the product exists, we can create the item
      ShoppingCart newCart =  new ShoppingCart(OffsetDateTime.now());
      newCart = cartRepository.save(newCart);
      Item newItem = new Item(newCart, product.get(), item.getQuantity());
      newCart.getItems().add(newItem);
      cartRepository.save(newCart);

      // save the item
      itemRepository.save(newItem);

      // get the cart
      return ResponseEntity.ok(newCart);
    

    // Optional<Product> product = productRepository.findById(item.getProduct().getId());
    // ShoppingCart cart = new ShoppingCart(OffsetDateTime.now());

    // ShoppingCart ct = cartRepository.save(cart);
    // Item it = new Item(ct, product.get(), item.getQuantity());
    // Item i = itemRepository.save(it);

    // ct.getItems().add(i);
    // ShoppingCart obj = cartRepository.save(ct);
    // return ResponseEntity.ok().body(obj);
  }

  @GetMapping
  public ResponseEntity<List<ShoppingCart>> getAllCarts() {

    // get all carts
    List<ShoppingCart> carts = cartRepository.findAll();
    return ResponseEntity.ok().body(carts);

    // List<ShoppingCart> carts = cartRepository.findAll();
    // return ResponseEntity.ok().body(carts);
  }
}
