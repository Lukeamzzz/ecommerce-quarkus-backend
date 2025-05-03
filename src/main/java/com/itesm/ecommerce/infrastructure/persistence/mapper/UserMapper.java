package com.itesm.ecommerce.infrastructure.persistence.mapper;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirebaseId(user.getFirebaseId());
        return userEntity;
    }

    public static User toDomain(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirebaseId(userEntity.getFirebaseId());
        return user;
    }
}
