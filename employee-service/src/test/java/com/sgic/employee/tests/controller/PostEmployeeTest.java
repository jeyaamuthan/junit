package com.sgic.employee.tests.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestClientException;

import com.sgic.employee.EmployeeTest;
import com.sgic.employee.server.dto.EmployeeDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PostEmployeeTest extends EmployeeTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unused")
	private EmployeeDto employee = new EmployeeDto();

//	common URL
	private String BASE_URL = "http://localhost:8084/";
//	Post API
	private String ADD_API_URL = "/employee";

//	Get API
	private String GET_API_URL = "/employee";

//	Save unit Test expected Response
	private static final String GET_EMPLOYEE_RESPONSE = "{\"statusCode\":20000,\"message\":\"OK\",\"results\":{\"listAllEmployee\":[{\"id\":1,\"firstName\":\"jeya\",\"lastName\":\"amuthan\",\"email\":\"amuthan@gmail.com\"}]}}";

	@Test
	public void testCreateEmployee() throws IOException, RestClientException {
//		EmployeeDto employeeDTO = new EmployeeDto("Dali", "SoftwareEngineer","dali@gmail.com");
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmail("amuthan@gmail.com");
		  employeeDto.setFirstName("jeya");
		  employeeDto.setLastName("amuthan");
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<EmployeeDto> request = new HttpEntity<EmployeeDto>(employeeDto, httpHeaders);
		ResponseEntity<String> postresponse = testRestTemplate
				.postForEntity(BASE_URL + ADD_API_URL, request, String.class);
		assertEquals(200, postresponse.getStatusCodeValue());

		ResponseEntity<String> getresponse = testRestTemplate.exchange(BASE_URL + GET_API_URL, HttpMethod.GET,
				new HttpEntity<>(httpHeaders), String.class);
		assertEquals(HttpStatus.OK, getresponse.getStatusCode());

		assertEquals(GET_EMPLOYEE_RESPONSE, getresponse.getBody());
	}

}
