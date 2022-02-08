package com.task1.demo.consumer;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.task1.demo.dto.AddressDTO;


@FeignClient(name="ADDRESS-SERVICE")
public interface AddressConsumer {

	@GetMapping("/address/retriveAddress/{addressid}")
	public AddressDTO retriveAddress(@PathVariable("addressid") Integer addressid);
	
	@GetMapping("/address/retriveAllAddress")
	public List<AddressDTO> retriveAllAddress();
}
