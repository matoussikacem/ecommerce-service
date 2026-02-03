package com.aretere.ecommerce.domain.usecase;

import com.aretere.ecommerce.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductUseCase {
    Product createProduct(String name, BigDecimal price, int stock, UUID categoryId);
    Product updateProduct(UUID id, String name, BigDecimal price, int stock);
    void deleteProduct(UUID id);
    Product getProduct(UUID id);
    List<Product> getAllProducts();
}
