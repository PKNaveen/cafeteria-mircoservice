package com.cafeteria.products.catalog.domain;

class ProductMapper {
    static Product toProduct(final ProductEntity productEntity) {
        return new Product(
                productEntity.getProduct_code(),
                productEntity.getProductName(),
                productEntity.getProduct_category(),
                productEntity.getProduct_price(),
                productEntity.getProduct_rating(),
                productEntity.getProduct_description(),
                productEntity.getProduct_imageURL());
    }
}
