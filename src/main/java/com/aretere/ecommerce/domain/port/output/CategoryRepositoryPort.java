package com.aretere.ecommerce.domain.port.output;

import com.aretere.ecommerce.domain.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepositoryPort {

    Category save(Category category);
    Optional<Category> findById(UUID id);
    List<Category> findAll();
    void deleteById(UUID id);
}
