package com.supera.games.domain.services;

import com.supera.games.domain.model.Item;
import com.supera.games.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ItemService {

  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public List<Item> getAllItems() {
    List<Item> items = itemRepository.findAll();
    return items;
  }

  public Item savaItem(Item item) {
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
