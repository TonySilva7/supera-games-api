package com.supera.games.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  public String image;
  private String name;
  private BigDecimal price;
  private Integer score;

  @Transient
  @OneToMany(mappedBy = "id.product")
  private Set<Item> items = new HashSet<>();

  public Product() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  // pega a lista de carrinhos onde o produto participou
  @Transient
  public Set<ShoppingCart> getCarts() {
    Set<ShoppingCart> carts = new HashSet<>();
    for (Item item : items) {
      carts.add(item.getCart());
    }

    return carts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return id.equals(product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
