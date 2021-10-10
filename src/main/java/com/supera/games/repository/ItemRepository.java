package com.supera.games.repository;

import com.supera.games.model.Item;
import com.supera.games.model.CartItemPK;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, CartItemPK> {
}
