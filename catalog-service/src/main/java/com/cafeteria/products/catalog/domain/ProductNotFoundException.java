package com.cafeteria.products.catalog.domain;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException notFound(String code) {
        return new ProductNotFoundException("Product not found with the code " + code);
    }
}
