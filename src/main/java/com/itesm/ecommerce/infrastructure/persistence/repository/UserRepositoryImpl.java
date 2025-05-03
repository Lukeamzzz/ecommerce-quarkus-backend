package com.itesm.ecommerce.infrastructure.persistence.repository;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.persistence.entity.ShoppingCartEntity;
import com.itesm.ecommerce.infrastructure.persistence.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.persistence.mapper.UserMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.ArrayList;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity, Integer> {
    @Override
    public User findByFirebaseId(String firebaseId) {
        UserEntity userEntity = find("firebaseId", firebaseId).firstResult();
        if (userEntity != null) {
            User user = new User();
            user.setId(userEntity.getId());
            user.setFirebaseId(userEntity.getFirebaseId());
            return user;
        }
        return null;
    }

    public UserEntity findUserById(int id) {
        return findById(id);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        persist(UserMapper.toEntity(user));
        flush();
        return user;
    }

}
