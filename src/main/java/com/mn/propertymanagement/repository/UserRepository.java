package com.mn.propertymanagement.repository;

import com.mn.propertymanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u where u.email = ?1 and u.password = ?2")
    Optional<UserEntity> findUserByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM UserEntity u where u.email = ?1")
    Optional<UserEntity> findUserByEmail(String email);

}
