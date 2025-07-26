package com.cafeteria.products.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Product code cannot be null")
    private String productCode;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Product name cannot be null")
    private String productName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Product category cannot be null")
    private String product_category;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Product price cannot be null")
    private String product_price;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Product rating cannot be null")
    private String product_rating;

    private String product_description;

    private String product_imageURL;

    public ProductEntity(
            String id,
            String productCode,
            String product_name,
            String product_category,
            String product_price,
            String product_rating,
            String product_description,
            String product_imageURL) {
        this.id = id;
        this.productCode = productCode;
        this.productName = product_name;
        this.product_category = product_category;
        this.product_price = product_price;
        this.product_rating = product_rating;
        this.product_description = product_description;
        this.product_imageURL = product_imageURL;
    }

    public ProductEntity() {}

    public String getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getProduct_category() {
        return product_category;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_rating() {
        return product_rating;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_imageURL() {
        return product_imageURL;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductCode(String product_code) {
        this.productCode = product_code;
    }

    public void setProductName(String product_name) {
        this.productName = product_name;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public void setProduct_rating(String product_rating) {
        this.product_rating = product_rating;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setProduct_imageURL(String product_imageURL) {
        this.product_imageURL = product_imageURL;
    }
}
