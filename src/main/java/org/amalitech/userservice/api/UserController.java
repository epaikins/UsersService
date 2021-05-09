package org.amalitech.userservice.api;

import java.util.List;
import java.util.stream.Collectors;

import org.amalitech.userservice.dtos.UserDTO;
import org.amalitech.userservice.entity.User;
import org.amalitech.userservice.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/users")
	public List<UserDTO> getAllUsers() {

		return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}
	
//	@GetMapping("/users/department/{id}")
//	public List<UserDTO> getAllUsersByDepartmentId(@PathVariable(name = "id") Integer id) {
//
//		return userService.getAllUsersByDepartmentId(id).stream().map(user -> modelMapper.map(user, UserDTO.class))
//				.collect(Collectors.toList());
//	}
	
	@GetMapping("/users/department/{department}")
	public List<UserDTO> getAllUsersByDepartmentName(@PathVariable(name = "department") String name) {

		return userService.getAllUsersByDepartmentName(name).stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/users/usergroup/{usergroup}")
	public List<UserDTO> getAllUsersByUserGroupName(@PathVariable(name = "usergroup") String name) {

		return userService.getAllUsersByUserGroupName(name).stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Integer id) {
		User user = userService.getUser(id);

		// convert entity to DTO
		UserDTO UserResponse = modelMapper.map(user, UserDTO.class);

		return ResponseEntity.ok().body(UserResponse);
	}
	
	@GetMapping("/user")
	public ResponseEntity<UserDTO> getUserById(@RequestParam String name) {
		System.out.println(name);
		System.out.println(name.getClass().getName());
		User user = userService.getUserByName(name);
		System.out.println(user.getName());
		// convert entity to DTO
		UserDTO UserResponse = modelMapper.map(user, UserDTO.class);

		return ResponseEntity.ok().body(UserResponse);
	}
	
	
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
		// convert DTO to entity
		
		
		User userRequest = modelMapper.map(userDTO, User.class);
//		Department department  = new Department(userDTO.getDepartmentId());
		
//		User userRequest = new User(UserDTO.getName(),UserDTO.getEmail(),UserDTO.getDepartmentId());
		User user = this.userService.createUser(userRequest);

		// convert entity to DTO
//		UserDTOResponse userResponse = new UserDTOResponse(userDTO.getName(), userDTO.getEmail(), userDTO.getDepartment().getId());

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
//		return new ResponseEntity<User>(userRequest, HttpStatus.CREATED);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO UserDTO) {

		// convert DTO to Entity
		User userRequest = modelMapper.map(UserDTO, User.class);

		User user = userService.updateUser(id, userRequest);

		// entity to DTO
		UserDTO userResponse = modelMapper.map(user, UserDTO.class);

		return ResponseEntity.ok().body(userResponse);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable(name = "id") Integer id) {
		userService.deleteUser(id);
		
		return ResponseEntity.noContent().build();
	}

}
