package com.cafeteria.products.catalog.domain;

import java.util.List;

public record PagedResult<T>(
        long totalElements,
        int pageNumber,
        int totalPages,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious,
        List<T> data) {}
