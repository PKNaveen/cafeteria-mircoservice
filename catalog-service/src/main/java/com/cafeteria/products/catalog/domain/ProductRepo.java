package com.cafeteria.products.catalog.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductCode(String code);
}
