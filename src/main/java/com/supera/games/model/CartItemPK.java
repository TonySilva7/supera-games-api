package com.supera.games.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cart")
public class CartItemPK implements Serializable {
  private static final long serialVersionUID = 476151177562655457L;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;

    result = prime * result + ((cart.getId() == null)
        ? 0
        : cart
        .getId()
        .hashCode());
    result = prime * result + ((product.getId() == null)
        ? 0
        : product
        .getId()
        .hashCode());

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
    CartItemPK other = (CartItemPK) obj;
    if (cart == null) {
      if (other.cart != null) {
        return false;
      }
    } else if (!cart.equals(other.cart)) {
      return false;
    }

    if (product == null) {
      if (other.product != null) {
        return false;
      }
    } else if (!product.equals(other.product)) {
      return false;
    }

    return true;
  }
}
