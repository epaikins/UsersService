package org.amalitech.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.amalitech.userservice.entity.User;
//import org.amalitech.userservice.repository.DepartmentRepository;
import org.amalitech.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
	@Autowired
	private UserRepository userRepository;
	


	public User getUser(Integer id) {
		  return userRepository.findById(id).orElseThrow();
	}
	
	public User getUserByName(String name) {
		User userNotFound = null;
		List<User> users = userRepository.findAll();
		for(User user : users) {
			
			System.out.println(user.getName());
			if(user.getName().compareTo(name) == 0) {
				return user;
			}
		}
		return userNotFound;
	}
	

	public List<User> getAllUsers() {
		  return (List<User>) userRepository.findAll();
		  }
	
//	public List<User> getAllUsersByDepartmentId(Integer id) {
//		  List<User> departmentUsers = new ArrayList<User>();
//		  List<User> users = userRepository.findAll();
//		  for(User user : users) {
//			  if(user.getDepartment().getId() == id) {
//				  departmentUsers.add(user);
//			  }
//		  }
//		  
//		
//		  return (List<User>) departmentUsers ;
//		  }
	
	public List<User> getAllUsersByDepartmentName(String name) {
		  List<User> departmentUsers = new ArrayList<User>();
		  List<User> users = userRepository.findAll();
		  for(User user : users) {
			  if(user.getDepartment().getName().compareTo(name) == 0) {
				  departmentUsers.add(user);
			  }
		  }
		  
		
		  return (List<User>) departmentUsers ;
		  }
	
	public List<User> getAllUsersByUserGroupName(String name) {
		  List<User> usergroupUsers = new ArrayList<User>();
		  List<User> users = userRepository.findAll();
		  for(User user : users) {
			  if(user.getUsergroup().getName().compareTo(name) == 0) {
				  usergroupUsers.add(user);
			  }
		  }
		  
		
		  return (List<User>) usergroupUsers ;
		  }
	
	
	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(Integer id, User UserRequest) {
		User User = userRepository.findById(id).orElseThrow();
		
		User.setName(UserRequest.getName());
		User.setEmail(UserRequest.getEmail());
		User.setDepartment(UserRequest.getDepartment());
		User.setUsergroup(UserRequest.getUsergroup());
		
		return userRepository.save(User);
	}

	public void deleteUser(Integer id) {
		User User = userRepository.findById(id).orElseThrow();
		
		userRepository.delete(User);
	}
	
}
