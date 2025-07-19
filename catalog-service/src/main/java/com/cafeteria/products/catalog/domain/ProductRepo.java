package com.cafeteria.products.catalog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepo extends JpaRepository<ProductEntity, Long> {}
