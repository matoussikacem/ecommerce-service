package com.aretere.ecommerce.infrastructure.adapter.input.web;

import com.aretere.ecommerce.domain.usecase.CategoryUseCase;
import com.aretere.ecommerce.infrastructure.adapter.input.web.dto.request.CategoryRequest;
import com.aretere.ecommerce.infrastructure.adapter.input.web.dto.response.CategoryResponse;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryUseCase categoryUseCase;

  public CategoryController(CategoryUseCase categoryUseCase) {
    this.categoryUseCase = categoryUseCase;
  }

  @PostMapping
  public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
    var category =
        categoryUseCase.createCategory(request.name(), request.description(), request.parentId());
    return ResponseEntity.created(URI.create("/api/categories/" + category.getId()))
        .body(CategoryResponse.from(category));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryResponse> update(
      @PathVariable UUID id, @RequestBody CategoryRequest request) {
    var category = categoryUseCase.updateCategory(id, request.name(), request.description());
    return ResponseEntity.ok(CategoryResponse.from(category));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    categoryUseCase.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> getById(@PathVariable UUID id) {
    var category = categoryUseCase.getCategory(id);
    return ResponseEntity.ok(CategoryResponse.from(category));
  }

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> getAll() {
    var categories =
        categoryUseCase.getAllCategories().stream().map(CategoryResponse::from).toList();
    return ResponseEntity.ok(categories);
  }
}
