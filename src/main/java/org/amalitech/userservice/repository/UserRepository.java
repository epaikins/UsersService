package org.amalitech.userservice.repository;

import org.amalitech.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmailAndPassword(String userEmail, String password);
}
