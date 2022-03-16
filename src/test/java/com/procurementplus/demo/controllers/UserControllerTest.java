package com.procurementplus.demo.controllers;

/*import static org.mockito.Mockito.doNothing;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.procurementplus.demo.dto.UserDto;
import com.procurementplus.demo.entity.UserEntity;
import com.procurementplus.demo.repositories.UserRepository;
import com.procurementplus.demo.services.UserService;

@WebMvcTest(controllers = UserController.class)*/
 class UserControllerTest {
	/*@InjectMocks
	UserService userServiceMock;

	@Mock
	UserRepository userRepositoryMock;

	@Autowired
	MockMvc mockMvc;

	static ObjectMapper mapper = new ObjectMapper();

	UserDto body;
	UserEntity userEntity;

	@BeforeEach
	void initData() {
		body = new UserDto();
		body.setSocialNumberSecurity("12345");
		body.setUserName("Edo");
		body.setDateOfBirth("1993-08-11");
		
		userEntity = new UserEntity();
		userEntity.setDeleted(false);
		
		userEntity.setSocialSecurityNumber(12345);
		userEntity.setUsername("Edo");
		userEntity.setDateOfBirth(new Date());
		
	}

	@Test
	void addnewUser_success_test() throws Exception {
			Mockito.when(userRepositoryMock.save(userEntity)).thenReturn(userEntity);
			doNothing().when(userServiceMock).addUser(body);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("v1/users")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void addnewUser_username_not_valid_min_length_test() throws Exception {
		body.setUserName("e");
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("v1/users")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void addnewUser_username_not_valid_max_length_test() throws Exception {
		body.setUserName("qwertyuiopasdfghjklmzxcvbnmqwertyuiopasfhhjkllksmnfhfghsssssss");
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("v1/users")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void addnewUser_username_not_valid_with_symbol_test() throws Exception {
		body.setUserName("@@ws");
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("v1/users")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	
	@Test
	void addnewUser_SSN_not_valid_max_length_test() throws Exception {
		body.setSocialNumberSecurity("123456");
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("v1/users")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void addnewUser_SSN_not_valid_min_length_test() throws Exception {
		body.setSocialNumberSecurity("123");
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("v1/users")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void addnewUser_SSN__valid_pad_zero_length_test() throws Exception {
		body.setSocialNumberSecurity("1234");
		Mockito.when(userRepositoryMock.save(userEntity)).thenReturn(userEntity);
		doNothing().when(userServiceMock).addUser(body);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("v1/users")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void addnewUser_SSN_valid__test() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("v1/users")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void putUser_success_test() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("v1/users")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	void putUser_not_valid_test() throws Exception {
		body.setSocialNumberSecurity("12345");
		body.setUserName("@@hjdhwa");
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("v1/users")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void delUser_success_test() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("v1/users/12345")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	
	@Test
	void delUser_inactive_data_test() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("v1/users/12345")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is(30000));
	}

	
	
	@Test
	void findUser_test() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("v1/users/12345")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	void findUser_not_found_test() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("v1/users/12345")
				.content(mapper.writeValueAsString(body));
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is(30000));
	}*/
	
}
