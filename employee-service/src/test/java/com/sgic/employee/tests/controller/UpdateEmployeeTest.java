package com.sgic.employee.tests.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestClientException;

import com.sgic.employee.EmployeeTest;
import com.sgic.employee.server.dto.EmployeeDto;


public class UpdateEmployeeTest extends EmployeeTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unused")
	private EmployeeDto employee = new EmployeeDto();

	// Save unit Test expected Response private static final String
	private static final String UPDATE_EMPLOYEE_RESPONSE = "Successfully Updated";

	@Test
	public void testUpdateEmployee() throws IOException, RestClientException {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmail("amuthan@gmail.com");
		  employeeDto.setFirstName("jeya");
		  employeeDto.setLastName("amuthan");
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<EmployeeDto> request = new HttpEntity<EmployeeDto>(employeeDto, httpHeaders);
		ResponseEntity<String> postresponse = testRestTemplate
				.postForEntity("http://localhost:8084" + "/employee", request, String.class);
		assertEquals(200, postresponse.getStatusCodeValue());

		EmployeeDto employeeDto1 = new EmployeeDto();
		  employeeDto.setEmail("amuthan@gmail.com");
		  employeeDto.setFirstName("sasikumar");
		  employeeDto.setLastName("sinthujan");
		HttpEntity<EmployeeDto> updaterequest = new HttpEntity<EmployeeDto>(employeeDto1, httpHeaders);
		ResponseEntity<String> putResponse = testRestTemplate.exchange(
				"http://localhost:8084" + "/employee" + 1, HttpMethod.PUT, updaterequest,
				String.class);

		assertEquals(UPDATE_EMPLOYEE_RESPONSE, putResponse.getBody());

	}
}
