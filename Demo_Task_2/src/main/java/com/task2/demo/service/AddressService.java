package com.task2.demo.service;

import java.util.List;

import com.task2.demo.dto.AddressDTO;

public interface AddressService {

	AddressDTO createAddress(AddressDTO addressDTO);
	AddressDTO retriveAddress(Integer addressid);
	List<AddressDTO> retriveAllAddress();
}
