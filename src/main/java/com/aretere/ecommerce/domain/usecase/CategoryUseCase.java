package com.aretere.ecommerce.domain.usecase;

import com.aretere.ecommerce.domain.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryUseCase {
  Category createCategory(String name, String description, UUID parentId);

  Category updateCategory(UUID id, String name, String description);

  void deleteCategory(UUID id);

  Category getCategory(UUID id);

  List<Category> getAllCategories();
}
