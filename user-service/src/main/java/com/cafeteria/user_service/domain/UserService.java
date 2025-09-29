package com.cafeteria.user_service.domain;

import com.cafeteria.user_service.Records.UserRecords;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<UserRecords> findById(UUID id) {
        return userRepo.findById(id).map(UserMapper::toUSer);
    }

    @KafkaListener(topics = "userCreation", groupId = "user-service-group")
    public void consume(UserRecords userRecords) {

//      Get message in json format under userRecords and map it to useEntity
        UserEntity userEntity = UserMapper.toEntity(userRecords);
        userRepo.save(userEntity);
    }
}
