package com.cafeteria.user_service.domain;

import com.cafeteria.user_service.Records.UserRecords;

class UserMapper {
    static UserRecords toUSer(final UserEntity userEntity) {
        return new UserRecords(
                userEntity.getUserId(),
                userEntity.getUserName(),
                userEntity.getUserEmail(),
                userEntity.getImageUrl(),
                userEntity.getCreatedDate().toString());
    }

    public static UserEntity toEntity(final UserRecords dto) {
        UserEntity entity = new UserEntity();
        entity.setUserName(dto.userName());
        entity.setUserEmail(dto.userEmail());
        entity.setImageUrl(dto.imageUrl());
        entity.setCreatedDate(java.time.LocalDateTime.parse(dto.createdDate()));
        return entity;
    }
}
