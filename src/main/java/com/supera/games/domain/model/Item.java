package com.supera.games.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
public class Item implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "product_id")
  private Product product;

//  @ManyToOne
//  @JoinColumn(name = "cart_id")
//  private Cart cart;

  private Integer quantity;
  private BigDecimal shipping;
  private BigDecimal price;

  public Item() {
  }

  public Item(Product product/*, Cart cart*/, Integer quantity) {
    this.product = product;
    this.quantity = quantity;
    this.shipping = new BigDecimal("10.00");
    this.price = product.getPrice();
    //this.cart = cart;
  }

  public Long getId() {
    return id;
  }


  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
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

  public BigDecimal getPrice() {
    return price;
  }

//  public Cart getCart() {
//    return cart;
//  }
//
//  public void setCart(Cart cart) {
//    this.cart = cart;
//  }

 @Transient
  public BigDecimal getTotalValue() {
    BigDecimal qnt = BigDecimal.valueOf(quantity);
    return qnt.multiply(product.getPrice());
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
