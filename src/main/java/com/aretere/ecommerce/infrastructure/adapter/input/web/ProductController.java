package com.aretere.ecommerce.infrastructure.adapter.input.web;

import com.aretere.ecommerce.domain.usecase.ProductUseCase;
import com.aretere.ecommerce.infrastructure.adapter.input.web.dto.request.ProductRequest;
import com.aretere.ecommerce.infrastructure.adapter.input.web.dto.response.ProductResponse;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductUseCase productUseCase;

  public ProductController(ProductUseCase productUseCase) {
    this.productUseCase = productUseCase;
  }

  @PostMapping
  public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {
    var product =
        productUseCase.createProduct(
            request.name(), request.price(), request.stock(), request.categoryId());
    return ResponseEntity.created(URI.create("/api/products/" + product.getId()))
        .body(ProductResponse.from(product));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> update(
      @PathVariable UUID id, @RequestBody ProductRequest request) {
    var product =
        productUseCase.updateProduct(id, request.name(), request.price(), request.stock());
    return ResponseEntity.ok(ProductResponse.from(product));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    productUseCase.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getById(@PathVariable UUID id) {
    var product = productUseCase.getProduct(id);
    return ResponseEntity.ok(ProductResponse.from(product));
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAll() {
    var products = productUseCase.getAllProducts().stream().map(ProductResponse::from).toList();
    return ResponseEntity.ok(products);
  }
}
