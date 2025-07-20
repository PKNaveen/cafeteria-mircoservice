package com.cafeteria.products.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(properties = {"spring.test.data.replace=none", "spring.datasource.url=jdbc:tc:postgresql:17-alpine:///db"})
@Sql("/test-data.sql")
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = productRepo.findAll();
        assertThat(products).hasSize(15);
    }
}
