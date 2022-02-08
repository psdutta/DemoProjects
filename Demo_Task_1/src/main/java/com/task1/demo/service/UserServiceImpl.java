package com.task1.demo.service;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.task1.demo.consumer.AddressConsumer;
import com.task1.demo.dto.Address;
import com.task1.demo.dto.AddressDTO;
import com.task1.demo.dto.UserDTO;
import com.task1.demo.entity.User;
import com.task1.demo.mapper.AddressMapper;
import com.task1.demo.mapper.UserMapper;
import com.task1.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	RestTemplate restTemplate;
	
	@Value("${address.getByID.url}")
	String addressByIdUrl;
	
	@Autowired
    private AddressConsumer addressConsumer;
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	private AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		logger.info("In createUser method of UserServiceImpl");
		User user = userMapper.toUser(userDTO);
		Integer id = userRepository.findAll().size() + 1;
		user.setUserid(id);
		User createdUser = userRepository.save(user);
		logger.info("Fetched user in createUser method UserServiceImpl");
		UserDTO createdUserDTO = userMapper.toUserDTO(createdUser);
		return createdUserDTO;
	}

	@Override
	public UserDTO retriveUser(Integer userid) {
		UserDTO userDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		logger.info("In retriveUser method of UserServiceImpl");
		Optional<User> user = userRepository.findById(userid);
		logger.info("Fetched user in retriveUser method UserServiceImpl");
		if(user.isPresent()) {
			userDTO = userMapper.toUserDTO(user.get());
			
			HttpEntity<String> request = new HttpEntity<>(null);
			ResponseEntity<AddressDTO> response = restTemplate
			  .exchange(addressByIdUrl+userDTO.getUserid(), HttpMethod.GET, request, AddressDTO.class);
			Address address = addressMapper.toAddress(response.getBody());
			userDTO.setAddress(address);
		}
		logger.info("returning user in retriveUser method of UserServiceImpl");
		return userDTO;
	}

	@Override
	public List<UserDTO> retriveAllUser() {
		logger.info("In retriveAllUser method of UserServiceImpl");
		List<User> allUser = userRepository.findAll();
		List<UserDTO> allUserDTO = userMapper.toAllUserDTO(allUser);
		logger.info("user converted in retriveAllUser method of UserServiceImpl");
		for(UserDTO userDTO:allUserDTO) {
			AddressDTO addressDTO = addressConsumer.retriveAddress(userDTO.getUserid());
			Address address = addressMapper.toAddress(addressDTO);
			userDTO.setAddress(address);
		}
		logger.info("returning user in retriveAllUser method of UserServiceImpl");
		return allUserDTO;
	}
	
}
