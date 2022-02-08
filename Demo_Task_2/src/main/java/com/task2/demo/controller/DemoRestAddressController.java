package com.task2.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task2.demo.dto.AddressDTO;
import com.task2.demo.exception.AddressNotfoundException;
import com.task2.demo.service.AddressService;


@RestController
@RequestMapping(path = "/address")
public class DemoRestAddressController {

	@Autowired
	private AddressService addressService;
	
	Logger logger = LoggerFactory.getLogger(DemoRestAddressController.class);

	@PostMapping(path= "/createAddress", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
		logger.info("In createAddress method of DemoRestAddressController");
		AddressDTO dto = addressService.createAddress(addressDTO);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}

	@GetMapping("/retriveAddress/{addressid}")
	public AddressDTO retriveAddress(@PathVariable Integer addressid) throws AddressNotfoundException{
		logger.info("In retriveAddress method of DemoRestAddressController");
		AddressDTO addressDTO = addressService.retriveAddress(addressid);
		if(addressDTO!=null) {
			return addressDTO;
		}
		else {
			throw new AddressNotfoundException();
		}
	}
	
	@GetMapping("/retriveAllAddress")
	public List<AddressDTO> retriveAllAddress() {
		logger.info("In retriveAllAddress method of DemoRestAddressController");
		return addressService.retriveAllAddress();
	}
}
