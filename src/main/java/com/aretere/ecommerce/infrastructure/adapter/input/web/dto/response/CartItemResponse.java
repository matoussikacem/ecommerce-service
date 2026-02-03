package com.aretere.ecommerce.infrastructure.adapter.input.web.dto.response;

import com.aretere.ecommerce.domain.model.CartItem;

import java.math.BigDecimal;

public record CartItemResponse(ProductResponse product, int quantity, BigDecimal subTotal) {
  static CartItemResponse from(CartItem item) {
    return new CartItemResponse(
        ProductResponse.from(item.product()), item.quantity(), item.getSubTotal());
  }
}
