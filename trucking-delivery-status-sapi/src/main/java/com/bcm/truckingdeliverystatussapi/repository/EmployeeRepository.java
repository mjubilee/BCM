package com.bcm.truckingdeliverystatussapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcm.truckingdeliverystatussapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}