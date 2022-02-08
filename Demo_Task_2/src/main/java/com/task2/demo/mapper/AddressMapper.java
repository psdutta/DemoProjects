package com.task2.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.task2.demo.dto.AddressDTO;
import com.task2.demo.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	AddressDTO toAddressDTO(Address address);
	
	List<AddressDTO> toAddressDTO(List<Address> address);
	
	Address toAddress(AddressDTO addressDTO);
}
