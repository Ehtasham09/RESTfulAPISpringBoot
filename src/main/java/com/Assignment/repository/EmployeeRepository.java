package com.Assignment.repository;

import java.awt.print.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Assignment.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
