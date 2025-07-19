package com.cafeteria.products.catalog.domain;

public record Product(
        String product_code,
        String product_name,
        String product_category,
        String product_price,
        String product_rating,
        String product_description,
        String product_imgURL) {}
