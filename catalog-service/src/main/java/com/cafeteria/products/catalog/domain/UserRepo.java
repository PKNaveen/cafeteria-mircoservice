package com.cafeteria.products.catalog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepo extends JpaRepository<UserEntity, Long> {}
