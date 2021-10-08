package com.supera.games.domain.services;

import com.supera.games.domain.model.Item;
import com.supera.games.domain.model.Product;
import com.supera.games.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
}
