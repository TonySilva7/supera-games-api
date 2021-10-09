package com.supera.games.domain.services;

import com.supera.games.domain.model.Item;
import com.supera.games.domain.model.Product;
import com.supera.games.domain.model.ShoppingCart;
import com.supera.games.domain.repository.ItemRepository;
import com.supera.games.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

  private final ItemRepository itemRepository;
  private final ProductRepository productRepository;

  public ItemService(ItemRepository itemRepository, ProductRepository productRepository) {
    this.itemRepository = itemRepository;
    this.productRepository = productRepository;
  }

  public List<Item> getAllItems() {
    List<Item> items = itemRepository.findAll();
    return items;
  }

  public Item saveItem(Item item) {
    // Cria um carrinho
    ShoppingCart cart = new ShoppingCart(OffsetDateTime.now());
    Optional<Product> product = productRepository.findById(item.getProduct().getId());

    item.setProduct(product.get());
    item.setCart(cart);

    Item obj = itemRepository.save(item);
    return obj;
  }

  public void removeItem(Long id) {
    itemRepository.deleteById(id);
  }

  public BigDecimal getSubTotal() {
    List<Item> items = getAllItems();
    BigDecimal subTotal = BigDecimal.ZERO;

    if (items.size() > 0) {
      subTotal = items.stream()
          .map(Item::getTotalValue)
          .reduce(BigDecimal.ZERO, BigDecimal::add)
          .setScale(2, RoundingMode.HALF_EVEN);

      return subTotal;
    }
    return subTotal;
  }

  public BigDecimal getTotal() {
    BigDecimal discountRange = new BigDecimal("250.00");
    BigDecimal total = BigDecimal.ZERO;
    BigDecimal subTotal = getSubTotal();

    BigDecimal totalShipping = getAllItems()
        .stream()
        .map(Item::getShipping)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

//    if(subTotal.compareTo(discountRange) > 250.00) {
//      total = subTotal;
//    } else {
//      total = subTotal.add(totalShipping);
//    }

    total = subTotal.compareTo(discountRange) == 1 ?
        subTotal :
        subTotal.add(totalShipping);

    return total.setScale(2, RoundingMode.HALF_EVEN);
  }
}
