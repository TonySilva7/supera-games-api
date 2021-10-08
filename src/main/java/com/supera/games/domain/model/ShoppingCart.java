package com.supera.games.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

  private List<Item> item = new ArrayList<>();

  private BigDecimal subtotal = BigDecimal.ZERO;
  private BigDecimal total = BigDecimal.ZERO;
  private BigDecimal totalShipping = BigDecimal.ZERO;
  private OffsetDateTime createdAt;
}
