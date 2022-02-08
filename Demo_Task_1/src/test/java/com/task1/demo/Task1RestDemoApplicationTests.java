package com.task1.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.task1.demo.controller.DemoRestUserController;
import com.task1.demo.dto.UserDTO;
import com.task1.demo.service.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class Task1RestDemoApplicationTests {

	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private DemoRestUserController demoRestUserController;
	
	@BeforeAll
	private void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(demoRestUserController).build();

	}
	
	@Test
	void testCreateUser() throws Exception {
		UserDTO userDTO = new UserDTO(1,"partha",33,true);
		
		Mockito.when(userService.retriveUser(userDTO.getUserid())).thenReturn(userDTO);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/user/createUser")
				.content(asJsonString(userDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testRetriveUser() throws Exception {
		UserDTO userDTO = new UserDTO(1,"partha",33,true);
		
		Mockito.when(userService.retriveUser(userDTO.getUserid())).thenReturn(userDTO);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/user/retriveUser?userid=1"))
				.andExpect(jsonPath("username", is("partha")));
		
	}
	
	@Test
	public void testRetriveAllUser() throws Exception {
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		UserDTO userDTO1 = new UserDTO(1,"partha",33,true);
		UserDTO userDTO2 = new UserDTO(1,"ronak",35,true);
		UserDTO userDTO3 = new UserDTO(1,"vijay",40,true);
		UserDTO userDTO4 = new UserDTO(1,"suraj",32,true);
		userDTOs.add(userDTO1);
		userDTOs.add(userDTO2);
		userDTOs.add(userDTO3);
		userDTOs.add(userDTO4);
		
		Mockito.when(userService.retriveAllUser()).thenReturn(userDTOs);
//		mockMvc.perform(MockMvcRequestBuilders
//				.get("/user/retriveAllUser"))
//				.andExpect(jsonPath("username", is("partha")));
		List result = userService.retriveAllUser();
		Mockito.verify(userService).retriveAllUser();
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
