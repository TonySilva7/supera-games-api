package com.supera.games.repository;

import com.supera.games.model.OrderProduct;
import com.supera.games.model.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
