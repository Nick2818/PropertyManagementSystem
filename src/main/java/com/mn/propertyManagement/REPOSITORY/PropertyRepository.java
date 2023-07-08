package com.mn.propertyManagement.REPOSITORY;

import com.mn.propertyManagement.ENTITY.PropertyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

}
