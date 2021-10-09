package com.supera.games.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private OffsetDateTime createdAt;

  @OneToMany(mappedBy = "id.cart")
  private Set<Item> items = new HashSet<>();

  // métodos utilitários
  public BigDecimal subtotal() {
    return null;
  }

  public BigDecimal total() {
    return null;
  }

  public BigDecimal totalShipping() {
    return null;
  }
}
