package com.supera.games.domain.repository;

import com.supera.games.domain.model.Item;
import com.supera.games.domain.model.pk.CartItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, CartItemPK> {
}
