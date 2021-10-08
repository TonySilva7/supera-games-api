package com.supera.games.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Item implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @OneToOne
  private Product product;

  @NonNull
  private Integer quantity;
  @NonNull
  private BigDecimal shipping;

  //---
  public BigDecimal getTotalValue() {
    BigDecimal qnt = BigDecimal.valueOf(quantity);
    return qnt.multiply(product.getPrice());
  }


  // --
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Item item = (Item) o;
    return Objects.equals(id, item.id);
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
