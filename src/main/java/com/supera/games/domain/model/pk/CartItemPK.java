package com.supera.games.domain.model.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.supera.games.domain.model.Product;
import com.supera.games.domain.model.ShoppingCart;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CartItemPK implements Serializable {

  private static final long serialVersionUID = 1L;

  @ManyToOne
  @JoinColumn(name = "cart_id")
  private ShoppingCart cart;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CartItemPK carItemPK = (CartItemPK) o;
    return cart.equals(carItemPK.cart) && product.equals(carItemPK.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cart, product);
  }
}
