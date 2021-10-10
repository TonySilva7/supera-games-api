package com.supera.games.domain.services;

import com.supera.games.domain.model.Cart;
import com.supera.games.domain.model.Item;
import com.supera.games.domain.model.Product;
import com.supera.games.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

  private final ItemRepository itemRepository;
  private final ProductService productService;
  private final CartService cartService;

  public ItemService(ItemRepository itemRepository, ProductService productService, CartService cartService) {
    this.itemRepository = itemRepository;
    this.productService = productService;
    this.cartService = cartService;
  }

  public List<Item> getAllItems() {
    List<Item> items = itemRepository.findAll();
    return items;
  }

  public Item savaItem(Item item) {
    Cart cart = new Cart();
    Optional<Product> product = productService.getProductById(item.getProduct().getId());
//    Optional<Cart> cart = cartService.getCartById(item.getCart().getId());

//    item.setProduct(product.get());
//    item.setCart(cart.get());

    Item kkk = new Item(product.get()/*, cart.get()*/, item.getQuantity());
    Item myItem = itemRepository.save(kkk);
    cart.getItems().add(myItem);
    cartService.saveCart();

    return myItem;
  }

  public void removeItem(Long id) {
    itemRepository.deleteById(id);
  }
}
