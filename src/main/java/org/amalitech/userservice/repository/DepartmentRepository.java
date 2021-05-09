package org.amalitech.userservice.repository;

import org.amalitech.userservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
//	Optional<Department> findByName(String name);
}
