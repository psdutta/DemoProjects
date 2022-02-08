package com.task1.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task1.demo.dto.UserDTO;
import com.task1.demo.exception.UserNotfoundException;
import com.task1.demo.jaxb.SoapClient;
import com.task1.demo.service.UserService;
import com.task1.demo.wsdlStub.DetailsType;
import com.task1.demo.wsdlStub.GetBankResponseType;
import com.task1.demo.wsdlStub.GetBankType;
import com.task1.demo.wsdlStub.ObjectFactory;


@RestController
@RequestMapping(path = "/user")
public class DemoRestUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SoapClient soapClient;
	
	Logger logger = LoggerFactory.getLogger(DemoRestUserController.class);

	@PostMapping(path= "/createUser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		logger.info("In createUser method of DemoRestUserController");
		UserDTO dto = userService.createUser(userDTO);
		logger.info("user saved in createUser method of DemoRestUserController");
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}

	@GetMapping("/retriveUser")
	public UserDTO retriveUser(@RequestParam Integer userid) {
		logger.info("In retriveUser method of DemoRestUserController");
		UserDTO userDTO = userService.retriveUser(userid);
		logger.info("user fetched in retriveUser method of DemoRestUserController");
		if(userDTO!=null) {
			return userDTO;
		}
		else {
			throw new UserNotfoundException();
		}
	}
	
	@GetMapping("/retriveAllUser")
	public List<UserDTO> retriveAllUser() {
		logger.info("In retriveAllUser method of DemoRestUserController");
		return userService.retriveAllUser();
	}
	
	@GetMapping("/retriveTestSoapData")
	public DetailsType getSoapData(@RequestParam String code) {
	ObjectFactory objectFactory = new ObjectFactory();
	GetBankType type = new GetBankType();
	type.setBlz(code);

	GetBankResponseType response = soapClient.getBank("http://www.thomas-bayer.com/axis2/services/BLZService",
	objectFactory.createGetBank(type));
	return response.getDetails();
	}
}
