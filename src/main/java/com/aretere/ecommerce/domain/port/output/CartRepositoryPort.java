package com.aretere.ecommerce.domain.port.output;

import com.aretere.ecommerce.domain.model.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepositoryPort {

    Cart save(Cart cart);
    Optional<Cart> findById(UUID id);
    void deleteById(UUID id);
}
