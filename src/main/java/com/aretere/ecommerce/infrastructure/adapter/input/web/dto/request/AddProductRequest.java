package com.aretere.ecommerce.infrastructure.adapter.input.web.dto.request;

import java.util.UUID;

public record AddProductRequest(UUID productId, int quantity) {}
