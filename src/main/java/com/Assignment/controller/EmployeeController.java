package com.Assignment.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.Service.EmployeeServiceImpl;
import com.Assignment.model.Employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@Operation(summary = "Add a new employee", description = "Creates a new employee and returns the created employee object.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Employee created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@Validated @RequestBody Employee employee) {
		Employee createdEmployee = employeeServiceImpl.addEmployee(employee);
		logger.info("Added employee: {}", createdEmployee);
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}

	@Operation(summary = "Get all employees", description = "Returns a list of all employees.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of employees retrieved successfully") })

	public ResponseEntity<Employee> getAllEmployees(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {
		Employee employees = employeeServiceImpl.getAllEmployees();
		logger.info("getAllEmployees List: {}", employees);

	    return new ResponseEntity<>(employees, HttpStatus.OK);
	}


	@Operation(summary = "Get an employee by ID", description = "Returns an employee object for the given ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee found"),
			@ApiResponse(responseCode = "404", description = "Employee not found") })
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(
			@Parameter(description = "ID of the employee to be retrieved") @PathVariable Long id) {
		Employee employee = employeeServiceImpl.getEmployeeById(id);
		logger.info("getEmployeeById: {}", employee);

		if (employee != null) {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Update an employee", description = "Updates an existing employee and returns the updated employee object.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
			@ApiResponse(responseCode = "404", description = "Employee not found"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(
			@Parameter(description = "ID of the employee to be updated") @PathVariable Long id,
			@Validated @RequestBody Employee employee) {
		Employee updatedEmployee = employeeServiceImpl.updateEmployee(id, employee);
		logger.info("updateEmployee: {}", updatedEmployee);

		if (updatedEmployee != null) {
			return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Delete an employee", description = "Deletes an employee by ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Employee not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(
			@Parameter(description = "ID of the employee to be deleted") @PathVariable Long id) {
		employeeServiceImpl.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
