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
}
