package com.task1.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.task1.demo.dto.UserDTO;
import com.task1.demo.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO toUserDTO(User user);
	
	List<UserDTO> toAllUserDTO(List<User> user);
	
	User toUser(UserDTO userDTO);
}
