package com.sgic.employee.tests.controller;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sgic.employee.EmployeeTest;
import com.sgic.employee.server.dto.EmployeeDto;


public class GetEmployeeIdTest extends EmployeeTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unused")
	private EmployeeDto employee = new EmployeeDto();

	private int id = 1;

	private static final String GET_BY_ID_RESPONSE = "{\"id\":1,\"firstName\":\"jeya\",\"lastName\":\"amuthan\",\"email\":\"amuthan@gmail.com\"}";

	@Test
	public void GetByIdTestSuccessfull() throws IOException {


		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmail("amuthan@gmail.com");
		  employeeDto.setFirstName("jeya");
		  employeeDto.setLastName("amuthan");
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<EmployeeDto> request = new HttpEntity<EmployeeDto>(employeeDto, httpHeaders);
		ResponseEntity<String> postresponse = testRestTemplate
				.postForEntity("http://localhost:8084/" + "/employee", request, String.class);

		ResponseEntity<String> getbyidresponse = testRestTemplate.exchange(
				"http://localhost:8084/" + "/empolyee" + "/" + id, HttpMethod.GET,
				new HttpEntity<>(httpHeaders), String.class);
		assertEquals(200, postresponse.getStatusCodeValue());
		assertEquals(GET_BY_ID_RESPONSE, getbyidresponse.getBody());
		assertEquals(HttpStatus.OK, getbyidresponse.getStatusCode());
	}

}
