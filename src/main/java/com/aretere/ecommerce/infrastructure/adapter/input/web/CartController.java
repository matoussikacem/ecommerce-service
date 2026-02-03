package com.aretere.ecommerce.infrastructure.adapter.input.web;

import com.aretere.ecommerce.domain.usecase.CartUseCase;
import com.aretere.ecommerce.infrastructure.adapter.input.web.dto.request.AddProductRequest;
import com.aretere.ecommerce.infrastructure.adapter.input.web.dto.request.UpdateQuantityRequest;
import com.aretere.ecommerce.infrastructure.adapter.input.web.dto.response.CartResponse;
import com.aretere.ecommerce.infrastructure.adapter.input.web.dto.response.CartTotalResponse;
import java.net.URI;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

  private final CartUseCase cartUseCase;

  public CartController(CartUseCase cartUseCase) {
    this.cartUseCase = cartUseCase;
  }

  @PostMapping
  public ResponseEntity<CartResponse> create() {
    var cart = cartUseCase.createCart();
    return ResponseEntity.created(URI.create("/api/carts/" + cart.getId()))
        .body(CartResponse.from(cart));
  }

  @PostMapping("/{cartId}/items")
  public ResponseEntity<Void> addProduct(
      @PathVariable UUID cartId, @RequestBody AddProductRequest request) {
    cartUseCase.addProductToCart(cartId, request.productId(), request.quantity());
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{cartId}/items/{productId}")
  public ResponseEntity<Void> removeProduct(
      @PathVariable UUID cartId, @PathVariable UUID productId) {
    cartUseCase.removeProductFromCart(cartId, productId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{cartId}/items/{productId}")
  public ResponseEntity<Void> updateQuantity(
      @PathVariable UUID cartId,
      @PathVariable UUID productId,
      @RequestBody UpdateQuantityRequest request) {
    cartUseCase.updateProductQuantity(cartId, productId, request.quantity());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{cartId}")
  public ResponseEntity<CartResponse> getCart(@PathVariable UUID cartId) {
    var cart = cartUseCase.getCart(cartId);
    return ResponseEntity.ok(CartResponse.from(cart));
  }

  @GetMapping("/{cartId}/total")
  public ResponseEntity<CartTotalResponse> getTotal(@PathVariable UUID cartId) {
    var total = cartUseCase.getCartTotal(cartId);
    return ResponseEntity.ok(new CartTotalResponse(total));
  }
}
