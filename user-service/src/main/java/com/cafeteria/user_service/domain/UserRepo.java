package com.cafeteria.user_service.domain;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepo extends JpaRepository<UserEntity, UUID> {}
