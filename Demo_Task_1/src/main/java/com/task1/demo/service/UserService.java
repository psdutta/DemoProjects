package com.task1.demo.service;


import java.util.List;

import com.task1.demo.dto.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO userDTO);
	UserDTO retriveUser(Integer userid);
	List<UserDTO> retriveAllUser();
}
