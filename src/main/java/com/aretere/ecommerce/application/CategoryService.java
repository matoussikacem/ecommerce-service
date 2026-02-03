package com.aretere.ecommerce.application;

import com.aretere.ecommerce.domain.model.Category;
import com.aretere.ecommerce.domain.port.output.CategoryRepositoryPort;
import com.aretere.ecommerce.domain.usecase.CategoryUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements CategoryUseCase {

  private final CategoryRepositoryPort categoryRepositoryPort;

  public CategoryService(CategoryRepositoryPort categoryRepositoryPort) {
    this.categoryRepositoryPort = categoryRepositoryPort;
  }

  @Override
  public Category createCategory(String name, String description, UUID parentId) {
    var category = new Category(name, description);

    if (parentId != null) {
      var parent =
          categoryRepositoryPort
              .findById(parentId)
              .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));
      parent.addSubCategory(category);
      categoryRepositoryPort.save(parent);
    }

    return categoryRepositoryPort.save(category);
  }

  @Override
  public Category updateCategory(UUID id, String name, String description) {
    return categoryRepositoryPort
        .findById(id)
        .map(
            category -> {
              category.updateName(name);
              category.updateDescription(description);
              return categoryRepositoryPort.save(category);
            })
        .orElseThrow(() -> new IllegalArgumentException("Category not found"));
  }

  @Override
  public void deleteCategory(UUID id) {
    categoryRepositoryPort.deleteById(id);
  }

  @Override
  public Category getCategory(UUID id) {
    return categoryRepositoryPort
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Category not found"));
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepositoryPort.findAll();
  }
}
