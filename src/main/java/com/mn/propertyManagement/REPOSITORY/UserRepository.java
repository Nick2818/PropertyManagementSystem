package com.mn.propertyManagement.REPOSITORY;

import com.mn.propertyManagement.ENTITY.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
