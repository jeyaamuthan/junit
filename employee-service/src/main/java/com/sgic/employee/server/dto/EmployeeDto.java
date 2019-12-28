package com.sgic.employee.server.dto;

public class EmployeeDto {
private Long id;
private String firstName;
private String lastName;
private String email;

public EmployeeDto( String firstName, String lastName, String email) {
	// TODO Auto-generated constructor stub
}
public EmployeeDto() {
	super();
	// TODO Auto-generated constructor stub
}
//public EmployeeDto() {
//	// TODO Auto-generated constructor stubs
//}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@Override
public String toString() {
	return "EmployeeDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
}


}
