package com.aretere.ecommerce.infrastructure.adapter.input.web.dto.response;

import com.aretere.ecommerce.domain.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String name, BigDecimal price, int stock) {
  public static ProductResponse from(Product product) {
    return new ProductResponse(
        product.getId(), product.getName(), product.getPrice(), product.getStock());
  }
}
