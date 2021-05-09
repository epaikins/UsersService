package org.amalitech.userservice.repository;

import org.amalitech.userservice.entity.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Integer>{

}
