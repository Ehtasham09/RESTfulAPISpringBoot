package com.Assignment.model;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;


public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Name cannot be empty")
	@Column(name = "name")
	private String name;

	@Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "department")
	private String department;

	 @Positive(message = "Salary must be a positive number")
	@Column(name = "salary")
	private Double salary;

	@Column(name = "joinningDate")
	private LocalDate joinningDate;
	
	//Due to issue for adding lombok jar file so i am creatig getter and setter method

	public Employee(long l, String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getJoinningDate() {
		return joinningDate;
	}

	public void setJoinningDate(LocalDate joinningDate) {
		this.joinningDate = joinningDate;
	}
	
	
}
