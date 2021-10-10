package com.supera.games.api;

import com.supera.games.domain.model.Item;
import com.supera.games.domain.model.Product;
import com.supera.games.domain.repository.ProductRepository;
import com.supera.games.domain.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

  private final ItemService itemService;
  private final ProductRepository productRepository;

  public ItemController(ItemService itemService, ProductRepository productRepository) {
    this.itemService = itemService;
    this.productRepository = productRepository;
  }

  @GetMapping
  public ResponseEntity<List<Item>> getItems() {
    List<Item> items = itemService.getAllItems();
    return ResponseEntity.ok().body(items);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity<Item> getItems(@RequestBody Item item) {
    Item obj = itemService.savaItem(item);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeItem(@PathVariable Long id) {
    itemService.removeItem(id);
    return ResponseEntity.noContent().build();
  }
}
