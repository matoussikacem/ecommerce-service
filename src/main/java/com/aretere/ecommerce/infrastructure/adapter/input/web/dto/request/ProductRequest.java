package com.aretere.ecommerce.infrastructure.adapter.input.web.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(String name, BigDecimal price, int stock, UUID categoryId) {}
