package com.Assignment.Service;

import java.util.List;

import com.Assignment.model.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);

	//List<Employee> getAllEmployees();

	Employee getEmployeeById(Long id);

	Employee updateEmployee(Long id, Employee employee);

	Employee deleteEmployee(Long id);

}
