package com.aretere.ecommerce.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Category {
    private final UUID id;
    private String name;
    private String description;
    private Category parent;
    private final List<Category> subCategories;
    private final List<Product> products;

    public Category(String name, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.subCategories = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public void addSubCategory(Category category) {
        category.parent = this;
        this.subCategories.add(category);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void removeSubCategory(UUID categoryId) {
        subCategories.removeIf(c -> c.getId().equals(categoryId));
    }

    public void removeProduct(UUID productId) {
        products.removeIf(p -> p.getId().equals(productId));
    }
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getParent() {
        return parent;
    }

    public List<Category> getSubCategories() {
        return List.copyOf(subCategories);
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

}
