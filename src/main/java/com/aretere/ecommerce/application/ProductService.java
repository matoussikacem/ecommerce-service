package com.aretere.ecommerce.application;

import com.aretere.ecommerce.domain.model.Product;
import com.aretere.ecommerce.domain.port.output.CategoryRepositoryPort;
import com.aretere.ecommerce.domain.port.output.ProductRepositoryPort;
import com.aretere.ecommerce.domain.usecase.ProductUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements ProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final CategoryRepositoryPort categoryRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort, CategoryRepositoryPort categoryRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Override
    public Product createProduct(String name, BigDecimal price, int stock, UUID categoryId) {
        var product = new Product(name, price, stock);

        var category = categoryRepositoryPort.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        category.addProduct(product);
        categoryRepositoryPort.save(category);

        return productRepositoryPort.save(product);
    }

    @Override
    public Product updateProduct(UUID id, String name, BigDecimal price, int stock) {
        return productRepositoryPort.findById(id)
                .map(product -> {
                    product.updateName(name);
                    product.updatePrice(price);
                    product.updateStock(stock);
                    return productRepositoryPort.save(product);
                })
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepositoryPort.deleteById(id);
    }

    @Override
    public Product getProduct(UUID id) {
        return productRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }
}
