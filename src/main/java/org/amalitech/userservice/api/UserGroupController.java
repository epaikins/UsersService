package org.amalitech.userservice.api;

import java.util.List;
import java.util.stream.Collectors;

import org.amalitech.userservice.dtos.UserGroupDTO;
import org.amalitech.userservice.entity.UserGroup;
import org.amalitech.userservice.service.UserGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserGroupController {

	@Autowired
	private UserGroupService userGroupService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/usergroups")
	public List<UserGroupDTO> getAllUserGroups() {

		return userGroupService.getAllUserGroups().stream()
				.map(userGroup -> modelMapper.map(userGroup, UserGroupDTO.class)).collect(Collectors.toList());
	}

	@GetMapping("/usergroup/{id}")
	public ResponseEntity<UserGroupDTO> getDepartmentById(@PathVariable(name = "id") Integer id) {
		UserGroup department = userGroupService.getUserGroup(id);

		// convert entity to DTO
		UserGroupDTO departmentResponse = modelMapper.map(department, UserGroupDTO.class);

		return ResponseEntity.ok().body(departmentResponse);
	}

	@PostMapping("/usergroup")
	public ResponseEntity<UserGroupDTO> createDepartment(@RequestBody UserGroupDTO userGroupDTO) {

		// convert DTO to entity
		UserGroup departmentRequest = modelMapper.map(userGroupDTO, UserGroup.class);

		UserGroup department = userGroupService.createUserGroup(departmentRequest);

		// convert entity to DTO
		UserGroupDTO departmentResponse = modelMapper.map(department, UserGroupDTO.class);

		return new ResponseEntity<UserGroupDTO>(departmentResponse, HttpStatus.CREATED);
	}

	@PutMapping("/usergroup/{id}")
	public ResponseEntity<UserGroupDTO> updateDepartment(@PathVariable Integer id,
			@RequestBody UserGroupDTO userGroupDTO) {

		// convert DTO to Entity
		UserGroup departmentRequest = modelMapper.map(userGroupDTO, UserGroup.class);

		UserGroup department = userGroupService.updateUserGroup(id, departmentRequest);

		// entity to DTO
		UserGroupDTO departmentResponse = modelMapper.map(department, UserGroupDTO.class);

		return ResponseEntity.ok().body(departmentResponse);
	}

	@DeleteMapping("/usergroup/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable(name = "id") Integer id) {
		userGroupService.deleteUserGroup(id);

		return ResponseEntity.noContent().build();
	}
}
