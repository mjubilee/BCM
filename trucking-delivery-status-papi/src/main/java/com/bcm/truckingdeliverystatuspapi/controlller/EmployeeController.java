package com.bcm.truckingdeliverystatuspapi.controlller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.bcm.truckingdeliverystatuspapi.client.EmployeeClient;
import com.bcm.truckingdeliverystatuspapi.configuration.EndpointConfiguration;
import com.bcm.truckingdeliverystatuspapi.deferred.EmployeeDeferred;
import com.bcm.truckingdeliverystatuspapi.model.EmployeeRequest;
import com.bcm.truckingdeliverystatuspapi.model.EmployeeResponse;

import jakarta.validation.Valid;


@RestController
public class EmployeeController {

Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired private Environment environment;
	@Autowired private EndpointConfiguration endpointConfiguration;
	@Autowired private EmployeeClient employeeClient;
	@Autowired private EmployeeDeferred employeeDeferred;
	
	private String port;
	private String host;
	private	String endpoint;
	
	private void setupLocalVariable() {
		this.port = environment.getProperty("local.server.port");
		this.host = environment.getProperty("HOSTNAME");
		this.endpoint = endpointConfiguration.getEmployee();
	}

	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/employees")
	public DeferredResult< ResponseEntity<List<EmployeeResponse> >> retrieveEmployeeList() {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveEmployeeList -- Retrieve employee list");

		return employeeDeferred.deferResponseList(employeeClient.submitRequestList(this.endpoint));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/employees/{id}")
	public DeferredResult< ResponseEntity<EmployeeResponse> > retrieveEmployee(@PathVariable String id) {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveEmployee -- Retrieve specific employee");
		
		return employeeDeferred.deferResponse(employeeClient.submitRequest(this.endpoint + '/' + id));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PutMapping("/employees")
	public DeferredResult< ResponseEntity<EmployeeResponse> > updateEmployee(@Valid @RequestBody EmployeeRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- updateEmployee -- update an employee");

		return employeeDeferred.deferResponse(employeeClient.updateRequest(this.endpoint, request));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PostMapping("/employees")
	public DeferredResult< ResponseEntity<EmployeeResponse> > insertEmployee(@Valid @RequestBody EmployeeRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- insertEmployee -- insert a new employee");

		return employeeDeferred.deferResponse(employeeClient.createRequest(this.endpoint, request));
	}
}
