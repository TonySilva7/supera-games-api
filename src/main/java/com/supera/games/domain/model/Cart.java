package com.supera.games.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private final OffsetDateTime createdAt;

  @JsonManagedReference
  @OneToMany/*(mappedBy = "cart")*/
  @JoinColumn(name = "cart_id")
  private final Set<Item> items = new HashSet<>();

  public Cart() {
    this.createdAt = OffsetDateTime.now();
  }


  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }


  public Set<Item> getItems() {
    return items;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
