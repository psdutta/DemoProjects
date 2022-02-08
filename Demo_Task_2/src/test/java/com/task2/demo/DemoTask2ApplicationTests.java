package com.task2.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.task2.demo.controller.DemoRestAddressController;
import com.task2.demo.dto.AddressDTO;
import com.task2.demo.service.AddressService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class DemoTask2ApplicationTests {

	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();
	
	@Mock
	private AddressService addressService;
	
	@InjectMocks
	private DemoRestAddressController demoRestAddressController;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(demoRestAddressController).build();

	}
	
	@Test
	void testCreateAddress() throws Exception {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressID(5);
		addressDTO.setUserID(1);
		addressDTO.setCity("hyderabad");
		addressDTO.setState("Telengana");
		addressDTO.setCountry("India");
		
		Mockito.when(addressService.createAddress(addressDTO)).thenReturn(addressDTO);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/address/createAddress")
				.content(asJsonString(addressDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testRetriveAddress() throws Exception {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressID(1);
		addressDTO.setUserID(1);
		addressDTO.setCity("kolkata");
		addressDTO.setState("West Bengal");
		addressDTO.setCountry("India");
		
		Mockito.when(addressService.retriveAddress(addressDTO.getAddressID())).thenReturn(addressDTO);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/address/retriveAddress/1"))
				.andExpect(jsonPath("city", is("kolkata")));
		
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
