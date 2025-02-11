package com.Assignment.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.Assignment.model.Employee;
import com.Assignment.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	  @Override
	    public Employee addEmployee(Employee employee) {
	        return employeeRepository.save(employee);
	    }
	  
	    
	    public Employee getAllEmployees() {
	        return (Employee) employeeRepository.findAll();
	    }
	    
	    @Override
	    public Employee getEmployeeById(Long id) {
	        Optional<Employee> employee = employeeRepository.findById(id);
	        return employee.orElse(null); 
	    }
	    

	    @Override
	    public Employee updateEmployee(Long id, Employee employee) {
	        if (!employeeRepository.existsById(id)) {
	            return null;
	        }
	        employee.setId(id); 
	        return employeeRepository.save(employee);
	    }
	    
	    @Override
	    public Employee deleteEmployee(Long id) {
	        if (employeeRepository.existsById(id)) {
	            employeeRepository.deleteById(id);
	        } else {
	            throw new EmployeeNotFoundException(id); 

	        }
			return null;
	    }

}
