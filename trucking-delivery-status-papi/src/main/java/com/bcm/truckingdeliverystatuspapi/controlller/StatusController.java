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

import com.bcm.truckingdeliverystatuspapi.client.StatusClient;
import com.bcm.truckingdeliverystatuspapi.configuration.EndpointConfiguration;
import com.bcm.truckingdeliverystatuspapi.deferred.StatusDeferred;
import com.bcm.truckingdeliverystatuspapi.model.StatusRequest;
import com.bcm.truckingdeliverystatuspapi.model.StatusResponse;

import jakarta.validation.Valid;


@RestController
public class StatusController {

Logger log = LoggerFactory.getLogger(StatusController.class);
	
	@Autowired private Environment environment;
	@Autowired private EndpointConfiguration endpointConfiguration;
	@Autowired private StatusClient statusClient;
	@Autowired private StatusDeferred statusDeferred;
	
	private String port;
	private String host;
	private	String endpoint;
	
	private void setupLocalVariable() {
		this.port = environment.getProperty("local.server.port");
		this.host = environment.getProperty("HOSTNAME");
		this.endpoint = endpointConfiguration.getStatus();
	}

	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/statuses")
	public DeferredResult< ResponseEntity<List<StatusResponse> >> retrieveStatusList() {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveStatusList -- Retrieve status list");

		return statusDeferred.deferResponseList(statusClient.submitRequestList(this.endpoint));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/statuses/{id}")
	public DeferredResult< ResponseEntity<StatusResponse> > retrieveStatus(@PathVariable String id) {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveStatus -- Retrieve specific status");
		
		return statusDeferred.deferResponse(statusClient.submitRequest(this.endpoint + '/' + id));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PutMapping("/statuses")
	public DeferredResult< ResponseEntity<StatusResponse> > updateStatus(@Valid @RequestBody StatusRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- updateStatus -- update a status");

		return statusDeferred.deferResponse(statusClient.updateRequest(this.endpoint, request));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PostMapping("/statuses")
	public DeferredResult< ResponseEntity<StatusResponse> > insertStatus(@Valid @RequestBody StatusRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- insertStatus -- insert a new status");

		return statusDeferred.deferResponse(statusClient.createRequest(this.endpoint, request));
	}
}
