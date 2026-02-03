package com.aretere.ecommerce.infrastructure.adapter.output.persistence;

import com.aretere.ecommerce.domain.model.Product;
import com.aretere.ecommerce.domain.port.output.ProductRepositoryPort;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {
  private final Map<UUID, Product> store = new ConcurrentHashMap<>();

  @Override
  public Product save(Product product) {
    store.put(product.getId(), product);
    return product;
  }

  @Override
  public Optional<Product> findById(UUID id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public List<Product> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public void deleteById(UUID id) {
    store.remove(id);
  }
}
