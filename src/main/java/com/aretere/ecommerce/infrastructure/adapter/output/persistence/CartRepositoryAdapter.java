package com.aretere.ecommerce.infrastructure.adapter.output.persistence;

import com.aretere.ecommerce.domain.model.Cart;
import com.aretere.ecommerce.domain.port.output.CartRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CartRepositoryAdapter implements CartRepositoryPort {
  private final Map<UUID, Cart> store = new ConcurrentHashMap<>();

  @Override
  public Cart save(Cart cart) {
    store.put(cart.getId(), cart);
    return cart;
  }

  @Override
  public Optional<Cart> findById(UUID id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public void deleteById(UUID id) {
    store.remove(id);
  }
}
