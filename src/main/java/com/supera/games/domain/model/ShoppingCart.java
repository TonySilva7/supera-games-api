package com.supera.games.domain.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
public class ShoppingCart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private OffsetDateTime createdAt;

//  @Transient
  @OneToMany(mappedBy = "id.cart", fetch = FetchType.LAZY)
  private Set<Item> items = new HashSet<>();

  public ShoppingCart() {
  }

  public ShoppingCart(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Set<Item> getItems() {
    return items;
  }

  public void setItems(Set<Item> items) {
    this.items = items;
  }

  @Transactional
  public BigDecimal subtotal() {
    return null;
  }

  @Transactional
  public BigDecimal total() {
    return null;
  }

  @Transactional
  public BigDecimal totalShipping() {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ShoppingCart cart = (ShoppingCart) o;
    return id.equals(cart.id) && createdAt.equals(cart.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt);
  }
}
