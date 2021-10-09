package com.supera.games.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private BigDecimal price;
    @NonNull
    private Integer score;
    @NonNull
    public String image;

    @OneToMany(mappedBy = "id.product")
    private Set<Item> items = new HashSet<>();


    // pega a lista de carrinhos onde o produto participou
    @JsonIgnore
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
