package com.aretere.ecommerce.domain.usecase;

import com.aretere.ecommerce.domain.model.Cart;

import java.math.BigDecimal;
import java.util.UUID;

public interface CartUseCase {

  Cart createCart();

  void addProductToCart(UUID cartId, UUID productId, int quantity);

  void removeProductFromCart(UUID cartId, UUID productId);

  void updateProductQuantity(UUID cartId, UUID productId, int quantity);

  BigDecimal getCartTotal(UUID cartId);

  Cart getCart(UUID cartId);
}
