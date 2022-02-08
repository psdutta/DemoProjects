package com.task1.demo.mapper;

import org.mapstruct.Mapper;

import com.task1.demo.dto.Address;
import com.task1.demo.dto.AddressDTO;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	Address toAddress(AddressDTO addressDTO);
}
