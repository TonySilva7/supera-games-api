package com.supera.games.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.supera.games.domain.model.pk.CartItemPK;

@Entity
@Table(name = "tb_cart_item")
public class Item implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private final CartItemPK id = new CartItemPK();

  private Integer quantity;
  private BigDecimal shipping;
  private BigDecimal price;

  public Item() {
  }

  //  public Item(ShoppingCart cart, Product product, Integer quantity, BigDecimal price) {
  public Item(ShoppingCart cart, Product product, Integer quantity) {
    id.setCart(cart);
    id.setProduct(product);

    this.quantity = quantity;
    this.shipping = new BigDecimal("10.00");
    this.price = product.getPrice();
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getShipping() {
    return shipping;
  }

  public void setShipping(BigDecimal shipping) {
    this.shipping = shipping;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @JsonIgnore
  public ShoppingCart getCart() {
    return id.getCart();
  }

  public void setCart(ShoppingCart cart) {
    id.setCart(cart);
  }

  public Product getProduct() {
    return id.getProduct();
  }

  public void setProduct(Product product) {
    id.setProduct(product);
  }


  @Transactional
  public BigDecimal getTotalValue() {
    BigDecimal qnt = BigDecimal.valueOf(quantity);
    return qnt.multiply(price);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return id.equals(item.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
