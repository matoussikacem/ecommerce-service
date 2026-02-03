package com.aretere.ecommerce.infrastructure.adapter.input.web.dto.request;

import java.util.UUID;

public record CategoryRequest(String name, String description, UUID parentId) {}
