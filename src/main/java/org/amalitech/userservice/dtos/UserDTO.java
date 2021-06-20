package org.amalitech.userservice.dtos;

import org.amalitech.userservice.entity.Department;
import org.amalitech.userservice.entity.UserGroup;

import lombok.Data;

@Data
public class UserDTO {
	private int id;
	private String name;
	private String email;
	private String password;
	private Department department;
	private UserGroup usergroup;
}
