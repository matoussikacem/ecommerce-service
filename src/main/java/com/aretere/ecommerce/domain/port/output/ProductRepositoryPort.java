package com.aretere.ecommerce.domain.port.output;

import com.aretere.ecommerce.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    List<Product> findAll();
    void deleteById(UUID id);
}
