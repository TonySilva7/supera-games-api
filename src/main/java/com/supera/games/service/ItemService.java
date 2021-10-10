package com.supera.games.service;

import com.supera.games.model.Item;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface ItemService {

  Item create(@NotNull(message = "The products for cart cannot be null.") @Valid Item item);
}
