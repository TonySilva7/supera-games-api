package com.supera.games.service;

import com.supera.games.model.Item;
import com.supera.games.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

  private ItemRepository itemRepository;

  public ItemServiceImpl(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Override
  public Item create(Item item) {
    return this.itemRepository.save(item);
  }
}
