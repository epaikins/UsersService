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

	@GetMapping(path = "/users", params = "departmentId")
	public List<UserDTO> getAllUsersByDepartmentId(@RequestParam(name = "departmentId") Integer id) {

		return userService.getAllUsersByDepartmentId(id).stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping(path = "/users", params = "departmentName")
	public List<UserDTO> getAllUsersByDepartmentName(@RequestParam(name = "departmentName") String name) {
		System.out.println(name);
		return userService.getAllUsersByDepartmentName(name).stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping(path = "/users", params = "usergroupId")
	public List<UserDTO> getAllUsersByUserGroupId(@RequestParam(name = "usergroupId") Integer id) {

		return userService.getAllUsersByUserGroupId(id).stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping(path = "/users", params = "usergroupName")
	public List<UserDTO> getAllUsersByUserGroupName(@RequestParam(name = "usergroupName") String name) {

		return userService.getAllUsersByUserGroupName(name).stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Integer id) {
		User user = userService.getUser(id);
		UserDTO UserResponse = modelMapper.map(user, UserDTO.class);

		return ResponseEntity.ok().body(UserResponse);
	}

	@GetMapping("/user")
	public ResponseEntity<UserDTO> getUserByName(@RequestParam String name) {
		User user = userService.getUserByName(name);
		UserDTO UserResponse = modelMapper.map(user, UserDTO.class);

		return ResponseEntity.ok().body(UserResponse);
	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
		User userRequest = modelMapper.map(userDTO, User.class);
		User user = this.userService.createUser(userRequest);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO UserDTO) {
		User userRequest = modelMapper.map(UserDTO, User.class);
		User user = userService.updateUser(id, userRequest);
		UserDTO userResponse = modelMapper.map(user, UserDTO.class);

		return ResponseEntity.ok().body(userResponse);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable(name = "id") Integer id) {
		userService.deleteUser(id);

		return ResponseEntity.noContent().build();
	}

}
