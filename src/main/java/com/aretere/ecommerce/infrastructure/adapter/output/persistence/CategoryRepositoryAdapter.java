package com.aretere.ecommerce.infrastructure.adapter.output.persistence;

import com.aretere.ecommerce.domain.model.Category;
import com.aretere.ecommerce.domain.port.output.CategoryRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

  private final Map<UUID, Category> store = new ConcurrentHashMap<>();

  @Override
  public Category save(Category category) {
    store.put(category.getId(), category);
    return category;
  }

  @Override
  public Optional<Category> findById(UUID id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public List<Category> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public void deleteById(UUID id) {
    store.remove(id);
  }
}
