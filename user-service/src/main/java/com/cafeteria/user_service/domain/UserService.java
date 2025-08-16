package com.cafeteria.user_service.domain;

import com.cafeteria.user_service.Records.UserRecords;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<UserRecords> findById(UUID id) {
        return userRepo.findById(id).map(UserMapper::toUSer); // This is correct
    }
}
