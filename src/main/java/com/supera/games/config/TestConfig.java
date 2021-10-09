package com.supera.games.config;

import com.supera.games.domain.model.Item;
import com.supera.games.domain.model.Product;
import com.supera.games.domain.model.ShoppingCart;
import com.supera.games.domain.repository.ItemRepository;
import com.supera.games.domain.repository.ProductRepository;
import com.supera.games.domain.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

  @Autowired
  private ProductRepository prodRepo;
  @Autowired
  private ShoppingCartRepository cartRepo;
  @Autowired
  private ItemRepository itemRepo;




  @Override
  public void run(String... args) throws Exception {

    Product p1 = new Product(312L, "Super Mario Odyssey", new BigDecimal("197.88"), 100, "super-mario-odyssey.png");
    Product p2 = new Product(201L, "Call Of Duty Infinite Warfare", new BigDecimal("49.99"), 80, "call-of-duty-infinite-warfare.png");
    Product p3 = new Product(102L, "The Witcher III Wild Hunt", new BigDecimal("119.5"), 250, "the-witcher-iii-wild-hunt.png");
    Product p4 = new Product(99L, "Call Of Duty WWII", new BigDecimal("249.99"), 205, "call-of-duty-wwii.png");
    Product p5 = new Product(12L,"Mortal Kombat XL", new BigDecimal("69.99"), 150, "mortal-kombat-xl.png");
    Product p6 = new Product(74L, "Shards of Darkness", new BigDecimal("71.94"), 400, "shards-of-darkness.png");
    Product p7 = new Product(31L, "Terra Média: Sombras de Mordor", new BigDecimal("79.99"), 50, "terra-media-sombras-de-mordor.png");
    Product p8 = new Product(420L, "FIFA 18", new BigDecimal("195.39"), 325, "fifa-18.png");
    Product p9 = new Product(501L, "Horizon Zero Dawn", new BigDecimal("115.8"), 290, "horizon-zero-dawn.png");
    prodRepo.save(p1);
    prodRepo.save(p8);

    ShoppingCart cart1 = new ShoppingCart(OffsetDateTime.now());
    cartRepo.save(cart1);


    Item item1 = new Item(cart1, p1, 2);
    Item item2 = new Item(cart1, p8, 1);
    itemRepo.saveAll(Arrays.asList(item1, item2));


  }
}
