package com.aretere.ecommerce.infrastructure.adapter.input.web.dto.response;

import com.aretere.ecommerce.domain.model.Cart;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartResponse(UUID id, List<CartItemResponse> items, BigDecimal total) {
  public static CartResponse from(Cart cart) {
    return new CartResponse(
        cart.getId(),
        cart.getItems().values().stream().map(CartItemResponse::from).toList(),
        cart.getTotal());
  }
}
