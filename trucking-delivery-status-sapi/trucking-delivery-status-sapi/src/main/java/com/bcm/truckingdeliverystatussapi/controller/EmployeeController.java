package com.bcm.truckingdeliverystatussapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bcm.truckingdeliverystatussapi.exception.EmployeeNotFoundException;
import com.bcm.truckingdeliverystatussapi.model.Employee;
import com.bcm.truckingdeliverystatussapi.repository.EmployeeRepository;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private Environment environment;

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> retrieveEmployeeList() {
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		
		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- retrieveEmployeeList -- Retrieve all employees");
		
		return new ResponseEntity<List<Employee>>(this.employeeRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> retrieveEmployeeById(@PathVariable Long id) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- retrieveEmployeeById -- Retrieve specific employee");
		
		Optional<Employee> employee = this.employeeRepo.findById(id);

		if (employee.isEmpty()) {			
			throw new EmployeeNotFoundException("Data with id = " + id + " can not be found.");
		}
		return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody Employee employee) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- insertEmployee -- add a new employee");
		
		Employee savedEmployee = employeeRepo.save(employee);				
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedEmployee.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> removeEmployee(@PathVariable Long id) {
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- removeEmployee -- remove an employee");
		
		Optional<Employee> queryEmployee = this.employeeRepo.findById(id);

		if (queryEmployee.isEmpty()) {			
			throw new EmployeeNotFoundException("Data with id = " + id + " can not be found.");
		}
		
		//instead of deleting the record, the employee will be disactivate
		//this.employeeRepo.delete(employee.get());
		queryEmployee.get().setActive(false);
		this.employeeRepo.save(queryEmployee.get());	
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(queryEmployee.get().getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/employees")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- updateEmployee -- update an employee");
		
		Optional<Employee> queryEmployee = this.employeeRepo.findById(employee.getId());

		if (queryEmployee.isEmpty()) {			
			throw new EmployeeNotFoundException("Data with id = " + employee.getId() + " can not be found.");
		}
		
		queryEmployee.get().setName(employee.getName());
		queryEmployee.get().setCategory_id(employee.getCategory_id());
		queryEmployee.get().setDriver_licence(employee.getDriver_licence());
		
		Employee savedEmployee = employeeRepo.save(queryEmployee.get());				
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedEmployee.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
