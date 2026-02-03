package com.aretere.ecommerce.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
  private final UUID id;
  private String name;
  private BigDecimal price;
  private int stock;

  public Product(String name, BigDecimal price, int stock) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.price = price;
    this.stock = stock;
  }

  public void updateName(String name) {
    this.name = name;
  }

  public void updatePrice(BigDecimal price) {
    this.price = price;
  }

  public void updateStock(int stock) {
    this.stock = stock;
  }

  public boolean isAvailable(int quantity) {
    return stock >= quantity;
  }

  public void decreaseStock(int quantity) {
    if (!isAvailable(quantity)) {
      throw new IllegalStateException("Stock insuffisant");
    }
    this.stock -= quantity;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public int getStock() {
    return stock;
  }
}
