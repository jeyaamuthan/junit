package com.sgic.employee.server.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.common.api.enums.RestApiResponseStatus;
import com.sgic.common.api.response.ApiResponse;
import com.sgic.common.api.response.BasicResponse;
import com.sgic.common.api.response.ContentResponse;
import com.sgic.common.api.response.ListContentResponse;
import com.sgic.common.api.response.ValidationFailure;
import com.sgic.employee.dto.mapper.Mapper;
import com.sgic.employee.server.dto.EmployeeDto;
import com.sgic.employee.server.entities.Employee;
import com.sgic.employee.server.services.EmployeeService;
import com.sgic.employee.server.util.Constants;
import com.sgic.employee.server.util.ErrorCodes;
import com.sgic.employee.server.util.ValidationMessages;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	ErrorCodes errorCodes;

	@Autowired
	private Mapper mapper;

	
	
	  private static final Logger logger = Logger.getLogger(EmployeeController.class);


	@PostMapping(value = "/employee")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDto employeeData) {
		if (employeeService.isEmailAlreadyExist(employeeData.getEmail())) {
		      logger.debug("Email already exists: createEmployee(), email: {}");
		      return new ResponseEntity<>(new BasicResponse<>(
		          new ValidationFailure(Constants.EMAIL, errorCodes.getEmailAlreadyExist()),
		          RestApiResponseStatus.VALIDATION_FAILURE,ValidationMessages.EMAIL_EXIST), HttpStatus.BAD_REQUEST);
		}
		Employee employee = mapper.map(employeeData, Employee.class);
		employeeService.createEmployee(employee);
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,"Employee Created successfully!!!"), HttpStatus.OK);
		
	}
	@GetMapping(value = "/employee")
	public ResponseEntity<Object> getEmployee() {
		List<Employee> employeeData = employeeService.getAllEmployee();
		List<EmployeeDto> employeeDtoData = mapper.map(employeeData, EmployeeDto.class);
		System.out.println(employeeDtoData);
		
		return new ResponseEntity<>(new ListContentResponse<EmployeeDto>("listAllEmployee",employeeDtoData, RestApiResponseStatus.OK), HttpStatus.OK);	
	}
	
		@PutMapping(value = "/employee")
		public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDto employeeDto) {
			Employee employee = mapper.map(employeeDto, Employee.class);
			employeeService.createEmployee(employee);
			return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.UPDATED), HttpStatus.OK);	
		}
		
	
		@DeleteMapping(value = "/employee/{id}")
		public ResponseEntity<Object> removeEmployee(@PathVariable Long id) {
			if(!employeeService.isIdExists(id)) {
				employeeService.deleteEmployee(id);
				return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.DELETED), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.DELETION_FAILURE), HttpStatus.CONFLICT);
			}
		}	
		
		
		@GetMapping(value = "/employee/{id}")
		public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
			Employee employee = employeeService.getEmployeeById(id);
			EmployeeDto employeeData = mapper.map(employee, EmployeeDto.class);
			return new ResponseEntity<>(new ContentResponse<EmployeeDto>("listEmployee", employeeData, RestApiResponseStatus.RECEIVED), HttpStatus.OK);	
		}
}
