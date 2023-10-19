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

import com.bcm.truckingdeliverystatuspapi.client.DriverAssignmentClient;
import com.bcm.truckingdeliverystatuspapi.configuration.EndpointConfiguration;
import com.bcm.truckingdeliverystatuspapi.deferred.DriverAssignmentDeferred;
import com.bcm.truckingdeliverystatuspapi.model.DriverAssignmentRequest;
import com.bcm.truckingdeliverystatuspapi.model.DriverAssignmentResponse;

import jakarta.validation.Valid;


@RestController
public class DriverAssignmentController {

Logger log = LoggerFactory.getLogger(DriverAssignmentController.class);
	
	@Autowired private Environment environment;
	@Autowired private EndpointConfiguration endpointConfiguration;
	@Autowired private DriverAssignmentClient driverAssignmentClient;
	@Autowired private DriverAssignmentDeferred driverAssignmentDeferred;
	
	private String port;
	private String host;
	private	String endpoint;
	
	private void setupLocalVariable() {
		this.port = environment.getProperty("local.server.port");
		this.host = environment.getProperty("HOSTNAME");
		this.endpoint = endpointConfiguration.getDriverAssignment();
	}

	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/driver-assignments")
	public DeferredResult< ResponseEntity<List<DriverAssignmentResponse> >> retrieveDriverAssignmentList() {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveDriverAssignmentList -- Retrieve driverAssignment list");

		return driverAssignmentDeferred.deferResponseList(driverAssignmentClient.submitRequestList(this.endpoint));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/driver-assignments/{id}")
	public DeferredResult< ResponseEntity<DriverAssignmentResponse> > retrieveDriverAssignment(@PathVariable String id) {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveDriverAssignment -- Retrieve specific driverAssignment");
		
		return driverAssignmentDeferred.deferResponse(driverAssignmentClient.submitRequest(this.endpoint + '/' + id));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PutMapping("/driver-assignments")
	public DeferredResult< ResponseEntity<DriverAssignmentResponse> > updateDriverAssignment(@Valid @RequestBody DriverAssignmentRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- updateDriverAssignment -- update an driverAssignment");

		return driverAssignmentDeferred.deferResponse(driverAssignmentClient.updateRequest(this.endpoint, request));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PostMapping("/driver-assignments")
	public DeferredResult< ResponseEntity<DriverAssignmentResponse> > insertDriverAssignment(@Valid @RequestBody DriverAssignmentRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- insertDriverAssignment -- insert a new driverAssignment");

		return driverAssignmentDeferred.deferResponse(driverAssignmentClient.createRequest(this.endpoint, request));
	}
}
