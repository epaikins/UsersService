package org.amalitech.userservice.service;

import java.util.List;

import org.amalitech.userservice.entity.UserGroup;
import org.amalitech.userservice.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGroupService{

	@Autowired
	private UserGroupRepository userGroupRepository;
	
	
	public UserGroup getUserGroup(Integer id) {
		  return userGroupRepository.findById(id).orElseThrow();
	}

	public List<UserGroup> getAllUserGroups() {
		  return (List<UserGroup>) userGroupRepository.findAll();
		  }
	

	public UserGroup createUserGroup(UserGroup userGroup) {
		System.out.println(userGroup);
		return userGroupRepository.save(userGroup);
	}


	public UserGroup updateUserGroup(Integer id, UserGroup userGroupRequest) {
		UserGroup userGroup = userGroupRepository.findById(id).orElseThrow();
		
		userGroup.setName(userGroupRequest.getName());
		return userGroupRepository.save(userGroup);
	}


	public void deleteUserGroup(Integer id) {
		UserGroup userGroup = userGroupRepository.findById(id)
				.orElseThrow();
		
		userGroupRepository.delete(userGroup);
	}
	
}
