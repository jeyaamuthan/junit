package com.sgic.employee.tests.controller;


import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestClientException;

import com.sgic.employee.EmployeeTest;
import com.sgic.employee.server.dto.EmployeeDto;


public class DeleteEmployeeTest extends EmployeeTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unused")
//	Use EmployeeDTO 
	private EmployeeDto employeeDTO = new EmployeeDto();

//	common URL
	private String BASE_URL = "http://localhost:8084";
//	Post API
	private String ADD_API_URL = "/employee";

//	Delete API
	private String DELETE_API_URL = "/employee/";

// Testing Id for delete Testing
	private int id = 1;

	@SuppressWarnings("unused")
//	Save unit Test expected Response
	private static final String ADD_EMPLOYEE_RESPONSE = "[{\"id\":1,\"firstName\":\"jeya\",\"lastName\":\"amuthan\",\"email\":\"amuthan@gmail.com\"}]";
	@SuppressWarnings("unused")

//	Save unit Test expected Response
	private static final String DELETE_EMPLOYEE_RESPONSE = "Deleted Successfully";

	@Test
	public void deleteEmployee() throws IOException, RestClientException {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmail("amuthan@gmail.com");
		  employeeDto.setFirstName("jeya");
		  employeeDto.setLastName("amuthan");
		HttpEntity<EmployeeDto> request = new HttpEntity<EmployeeDto>(employeeDTO, httpHeaders);
		ResponseEntity<String> postResponse = testRestTemplate.postForEntity(BASE_URL + ADD_API_URL, request,
				String.class);
		assertEquals(200, postResponse.getStatusCodeValue());

		ResponseEntity<String> getResponse = testRestTemplate.exchange(BASE_URL + DELETE_API_URL + id,
				HttpMethod.DELETE, new HttpEntity<>(httpHeaders), String.class);
		assertEquals(200, getResponse.getStatusCodeValue());
		assertEquals(DELETE_EMPLOYEE_RESPONSE, getResponse.getBody());

	}

}
