package com.aretere.ecommerce.domain.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cart {
  private final UUID id;
  private final Map<UUID, CartItem> items;

  public Cart() {
    this.id = UUID.randomUUID();
    this.items = new HashMap<>();
  }

  public void addProduct(Product product, int quantity) {
    items.compute(
        product.getId(),
        (k, existing) ->
            existing == null
                ? new CartItem(product, quantity)
                : existing.increaseQuantity(quantity));
  }

  public void removeProduct(UUID productId) {
    items.remove(productId);
  }

  public void updateQuantity(UUID productId, int quantity) {
    if (quantity <= 0) {
      removeProduct(productId);
    } else {
      items.computeIfPresent(productId, (k, item) -> item.updateQuantity(quantity));
    }
  }

  public BigDecimal getTotal() {
    return items.values().stream()
        .map(CartItem::getSubTotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public UUID getId() {
    return id;
  }

  public Map<UUID, CartItem> getItems() {
    return Map.copyOf(items);
  }
}
