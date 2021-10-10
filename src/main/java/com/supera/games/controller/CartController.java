package com.supera.games.controller;

import com.sun.istack.NotNull;
import com.supera.games.dto.CartProductDto;
import com.supera.games.exception.ResourceNotFoundException;
import com.supera.games.model.Cart;
import com.supera.games.model.Item;
import com.supera.games.model.Product;
import com.supera.games.model.Status;
import com.supera.games.service.ItemService;
import com.supera.games.service.CartService;
import com.supera.games.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carts")
public class CartController {

  ProductService productService;
  CartService cartService;
  ItemService itemService;

  public CartController(ProductService productService, CartService cartService, ItemService itemService) {
    this.productService = productService;
    this.cartService = cartService;
    this.itemService = itemService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public @NotNull
  Iterable<Cart> list() {
    return this.cartService.getAllCarts();
  }

  @PostMapping
//  public ResponseEntity<Cart> create(@RequestBody CartForm form) {
  public ResponseEntity<Cart> create(@RequestBody Item item) {



    Cart cart = new Cart();
    cart.setStatus(Status.PAID.name());
    cart = this.cartService.create(cart);

    Product kkk = item.getProduct();

//    Product p = productService.getProduct(item.getProduct().getId());
    Item it = new Item(cart, item.getProduct(), item.getQuantity());
    itemService.create(it);

    cart.getCartProducts().add(it);

    return ResponseEntity.ok().body(cart);





    //----------------------------------------------------
    /*
    List<CartProductDto> formDtos = form.getProductCarts();
    validateProductsExistence(formDtos);
    Cart cart = new Cart();
    cart.setStatus(Status.PAID.name());
    cart = this.cartService.create(cart);

    List<Item> items = new ArrayList<>();
    for (CartProductDto dto : formDtos) {
      items.add(itemService.create(new Item(cart, productService.getProduct(dto
          .getProduct()
          .getId()), dto.getQuantity())));
    }

    cart.setCartProducts(items);

    this.cartService.update(cart);

    String uri = ServletUriComponentsBuilder
        .fromCurrentServletMapping()
        .path("/carts/{id}")
        .buildAndExpand(cart.getId())
        .toString();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", uri);

    return new ResponseEntity<>(cart, headers, HttpStatus.CREATED);

     */
  }

  private void validateProductsExistence(List<CartProductDto> cartProducts) {
    List<CartProductDto> list = cartProducts
        .stream()
        .filter(op -> Objects.isNull(productService.getProduct(op
            .getProduct()
            .getId())))
        .collect(Collectors.toList());

    if (!CollectionUtils.isEmpty(list)) {
      new ResourceNotFoundException("Product not found");
    }
  }

  public static class CartForm {

    private List<CartProductDto> productCarts;

    public List<CartProductDto> getProductCarts() {
      return productCarts;
    }

    public void setProductCarts(List<CartProductDto> productCarts) {
      this.productCarts = productCarts;
    }
  }
}
