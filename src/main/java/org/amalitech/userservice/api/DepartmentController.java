package org.amalitech.userservice.api;

import java.util.List;
import java.util.stream.Collectors;

import org.amalitech.userservice.dtos.DepartmentDTO;
import org.amalitech.userservice.entity.Department;
import org.amalitech.userservice.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/departments")
	public List<DepartmentDTO> getAllDepartments() {

		return departmentService.getAllDepartments().stream().map(department -> modelMapper.map(department, DepartmentDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/department/{id}")
	public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "id") Integer id) {
		Department department = departmentService.getDepartment(id);

		// convert entity to DTO
		DepartmentDTO departmentResponse = modelMapper.map(department, DepartmentDTO.class);

		return ResponseEntity.ok().body(departmentResponse);
	}
	
	@PostMapping("/department")
	public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {

		// convert DTO to entity
		Department departmentRequest = modelMapper.map(departmentDTO, Department.class);

		Department department = departmentService.createDepartment(departmentRequest);

		// convert entity to DTO
		DepartmentDTO departmentResponse = modelMapper.map(department, DepartmentDTO.class);

		return new ResponseEntity<DepartmentDTO>(departmentResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/department/{id}")
	public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Integer id, @RequestBody DepartmentDTO departmentDTO) {

		// convert DTO to Entity
		Department departmentRequest = modelMapper.map(departmentDTO, Department.class);

		Department department = departmentService.updateDepartment(id, departmentRequest);

		// entity to DTO
		DepartmentDTO departmentResponse = modelMapper.map(department, DepartmentDTO.class);

		return ResponseEntity.ok().body(departmentResponse);
	}

	@DeleteMapping("/department/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable(name = "id") Integer id) {
		departmentService.deleteDepartment(id);
		
		return ResponseEntity.noContent().build();
	}
}
