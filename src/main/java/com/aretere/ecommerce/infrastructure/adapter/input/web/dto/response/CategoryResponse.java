package com.aretere.ecommerce.infrastructure.adapter.input.web.dto.response;

import com.aretere.ecommerce.domain.model.Category;

import java.util.List;
import java.util.UUID;

public record CategoryResponse(
    UUID id,
    String name,
    String description,
    List<CategoryResponse> subCategories,
    List<ProductResponse> products) {
  public static CategoryResponse from(Category category) {
    return new CategoryResponse(
        category.getId(),
        category.getName(),
        category.getDescription(),
        category.getSubCategories().stream().map(CategoryResponse::from).toList(),
        category.getProducts().stream().map(ProductResponse::from).toList());
  }
}
