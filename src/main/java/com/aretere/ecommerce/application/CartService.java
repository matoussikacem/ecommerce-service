package com.aretere.ecommerce.application;

import com.aretere.ecommerce.domain.model.Cart;
import com.aretere.ecommerce.domain.port.output.CartRepositoryPort;
import com.aretere.ecommerce.domain.port.output.ProductRepositoryPort;
import com.aretere.ecommerce.domain.usecase.CartUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CartService implements CartUseCase {
  private final CartRepositoryPort cartRepositoryPort;
  private final ProductRepositoryPort productRepositoryPort;

  public CartService(
      CartRepositoryPort cartRepositoryPort, ProductRepositoryPort productRepositoryPort) {
    this.cartRepositoryPort = cartRepositoryPort;
    this.productRepositoryPort = productRepositoryPort;
  }

  @Override
  public Cart createCart() {
    return cartRepositoryPort.save(new Cart());
  }

  @Override
  public void addProductToCart(UUID cartId, UUID productId, int quantity) {
    var cart =
        cartRepositoryPort
            .findById(cartId)
            .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

    var product =
        productRepositoryPort
            .findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    cart.addProduct(product, quantity);
    cartRepositoryPort.save(cart);
  }

  @Override
  public void removeProductFromCart(UUID cartId, UUID productId) {
    var cart =
        cartRepositoryPort
            .findById(cartId)
            .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

    cart.removeProduct(productId);
    cartRepositoryPort.save(cart);
  }

  @Override
  public void updateProductQuantity(UUID cartId, UUID productId, int quantity) {
    var cart =
        cartRepositoryPort
            .findById(cartId)
            .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

    cart.updateQuantity(productId, quantity);
    cartRepositoryPort.save(cart);
  }

  @Override
  public BigDecimal getCartTotal(UUID cartId) {
    return cartRepositoryPort
        .findById(cartId)
        .map(Cart::getTotal)
        .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
  }

  @Override
  public Cart getCart(UUID cartId) {
    return cartRepositoryPort
        .findById(cartId)
        .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
  }
}
