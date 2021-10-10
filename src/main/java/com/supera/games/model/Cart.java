package com.supera.games.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cartProducts")
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateCreated;

  private String status;

  @OneToMany(mappedBy = "pk.cart")
  @Valid
  private List<Item> items = new ArrayList<>();

  @Transient
  public Double getTotalCartPrice() {
    double sum = 0D;
    List<Item> items = getCartProducts();
    for (Item op : items) {
      sum += op.getTotalPrice();
    }

    return sum;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Item> getCartProducts() {
    return items;
  }

  public void setCartProducts(List<Item> items) {
    this.items = items;
  }

  @Transient
  public int getNumberOfProducts() {
    return this.items.size();
  }
}
