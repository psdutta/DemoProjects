package com.task2.demo.service;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task2.demo.dto.AddressDTO;
import com.task2.demo.entity.Address;
import com.task2.demo.mapper.AddressMapper;
import com.task2.demo.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	private AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
	
	@Override
	public AddressDTO createAddress(AddressDTO addressDTO) {
		logger.info("In createAddress method of AddressServiceImpl");
		Address address = addressMapper.toAddress(addressDTO);
		Integer id = addressRepository.findAll().size() + 1;
		address.setAddressID(id);
		Address createdAddress = addressRepository.save(address);
		logger.info("Fetched user in createAddress method AddressServiceImpl");
		AddressDTO createdAddressDTO = addressMapper.toAddressDTO(createdAddress);
		return createdAddressDTO;
	}

	@Override
	public AddressDTO retriveAddress(Integer addressid) {
		AddressDTO addressDTO =null;
		logger.info("In retriveAddress method of AddressServiceImpl");
		Optional<Address> address = addressRepository.findById(addressid);
		logger.info("Fetched user in retriveAddress method AddressServiceImpl size "+address.isEmpty()+" "+address.isPresent());
		if(address.isPresent()) {
			addressDTO = addressMapper.toAddressDTO(address.get());
		}
		return addressDTO;
	}

	@Override
	public List<AddressDTO> retriveAllAddress() {
		logger.info("In retriveAllAddress method of AddressServiceImpl");
		List<Address> addressList = addressRepository.findAll();
		logger.info("Fetched user in retriveAllAddress method AddressServiceImpl");
		List<AddressDTO> addressDTOList = addressMapper.toAddressDTO(addressList);
		return addressDTOList;
	}

}
