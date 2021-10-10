package com.supera.games.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Item {

  @EmbeddedId
  @JsonIgnore
  private CartItemPK pk;

  @Column(nullable = false)
  private Integer quantity;

  public Item() {
    super();
  }

  public Item(Cart cart, Product product, Integer quantity) {
    pk = new CartItemPK();
    pk.setCart(cart);
    pk.setProduct(product);
    this.quantity = quantity;
  }

  @Transient
  public Product getProduct() {
    return this.pk.getProduct();
  }

  @Transient
  public Double getTotalPrice() {
    return getProduct().getPrice() * getQuantity();
  }

  public CartItemPK getPk() {
    return pk;
  }

  public void setPk(CartItemPK pk) {
    this.pk = pk;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((pk == null) ? 0 : pk.hashCode());

    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Item other = (Item) obj;
    if (pk == null) {
      if (other.pk != null) {
        return false;
      }
    } else if (!pk.equals(other.pk)) {
      return false;
    }

    return true;
  }
}
