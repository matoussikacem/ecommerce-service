package com.aretere.ecommerce.domain.model;

import java.math.BigDecimal;

public record CartItem(Product product, int quantity) {

  public CartItem increaseQuantity(int amount) {
    return new CartItem(product, quantity + amount);
  }

  public CartItem updateQuantity(int newQuantity) {
    return new CartItem(product, newQuantity);
  }

  public BigDecimal getSubTotal() {
    return product.getPrice().multiply(BigDecimal.valueOf(quantity));
  }
}
