package org.amalitech.userservice.service;

import java.util.List;

import org.amalitech.userservice.entity.Department;
import org.amalitech.userservice.errors.exceptions.NotFoundException;
import org.amalitech.userservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	private String errorMessage = "Department Not Found";

	public Department getDepartment(Integer id) {
		return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException(errorMessage));
	}

	public List<Department> getAllDepartments() {
		return (List<Department>) departmentRepository.findAll();
	}

	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department updateDepartment(Integer id, Department departmentRequest) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(errorMessage));

		department.setName(departmentRequest.getName());
		return departmentRepository.save(department);
	}

	public void deleteDepartment(Integer id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(errorMessage));

		departmentRepository.delete(department);
	}

}
