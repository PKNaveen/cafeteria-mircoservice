package com.cafeteria.products.catalog.web.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.cafeteria.products.catalog.AbstractIT;
import com.cafeteria.products.catalog.domain.Product;
import com.cafeteria.products.catalog.domain.ProductService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Autowired
    private ProductService productService;

    @Test
    void shouldReturnProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(15))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldReturnProductByCode() {
        Product product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", "P1001")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);

        assertThat(product.product_code()).isEqualTo("P1001");
        assertThat(product.product_name()).isEqualTo("Laptop Pro");
        assertThat(product.product_description()).isEqualTo("High-performance laptop");
        assertThat(product.product_price()).isEqualTo("79999");
    }

    @Test
    void shouldReturnNotFoundWhenProductCodeNotFound() {
        String productCode = "invalid-product-code";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", productCode)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("detail", is("Product not found with the code " + productCode));
    }
}
