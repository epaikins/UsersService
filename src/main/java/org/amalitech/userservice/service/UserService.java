package org.amalitech.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.amalitech.userservice.entity.User;
import org.amalitech.userservice.errors.exceptions.NotFoundException;
//import org.amalitech.userservice.repository.DepartmentRepository;
import org.amalitech.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	private String errorMessage = "User Not Found";

	public User getUser(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new NotFoundException(errorMessage));
	}

	public User getUserByName(String name) {
		User userNotFound = null;
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getName().compareTo(name) == 0) {
				return user;
			}
		}
		return userNotFound;
	}

	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public List<User> getAllUsersByDepartmentId(Integer id) {
		List<User> departmentUsers = new ArrayList<User>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getDepartment().getId() == id) {
				departmentUsers.add(user);
			}
		}

		return (List<User>) departmentUsers;
	}

	public List<User> getAllUsersByDepartmentName(String name) {
		System.out.println(name);
		List<User> departmentUsers = new ArrayList<User>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getDepartment().getName().compareTo(name) == 0) {
				departmentUsers.add(user);
			}
		}

		return (List<User>) departmentUsers;
	}

	public List<User> getAllUsersByUserGroupId(int id) {
		List<User> usergroupUsers = new ArrayList<User>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getUsergroup().getId() == id) {
				usergroupUsers.add(user);
			}
		}

		return (List<User>) usergroupUsers;
	}

	public List<User> getAllUsersByUserGroupName(String name) {
		List<User> usergroupUsers = new ArrayList<User>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getUsergroup().getName().compareTo(name) == 0) {
				usergroupUsers.add(user);
			}
		}

		return (List<User>) usergroupUsers;
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(Integer id, User UserRequest) {
		User User = userRepository.findById(id).orElseThrow(() -> new NotFoundException(errorMessage));

		if (UserRequest.getName() != null) {
			User.setName(UserRequest.getName());
		}

		if (UserRequest.getEmail() != null) {
			User.setEmail(UserRequest.getEmail());
		}

		if (UserRequest.getDepartment() != null) {
			User.setDepartment(UserRequest.getDepartment());
		}

		if (UserRequest.getUsergroup() != null) {
			User.setUsergroup(UserRequest.getUsergroup());
		}

		return userRepository.save(User);
	}

	public void deleteUser(Integer id) {
		User User = userRepository.findById(id).orElseThrow(() -> new NotFoundException(errorMessage));

		userRepository.delete(User);
	}

}
