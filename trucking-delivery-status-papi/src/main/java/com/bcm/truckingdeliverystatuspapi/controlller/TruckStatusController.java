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

import com.bcm.truckingdeliverystatuspapi.client.TruckStatusClient;
import com.bcm.truckingdeliverystatuspapi.configuration.EndpointConfiguration;
import com.bcm.truckingdeliverystatuspapi.deferred.TruckStatusDeferred;
import com.bcm.truckingdeliverystatuspapi.model.TruckStatusRequest;
import com.bcm.truckingdeliverystatuspapi.model.TruckStatusResponse;

import jakarta.validation.Valid;


@RestController
public class TruckStatusController {

Logger log = LoggerFactory.getLogger(TruckStatusController.class);
	
	@Autowired private Environment environment;
	@Autowired private EndpointConfiguration endpointConfiguration;
	@Autowired private TruckStatusClient truckStatusClient;
	@Autowired private TruckStatusDeferred truckStatusDeferred;
	
	private String port;
	private String host;
	private	String endpoint;
	
	private void setupLocalVariable() {
		this.port = environment.getProperty("local.server.port");
		this.host = environment.getProperty("HOSTNAME");
		this.endpoint = endpointConfiguration.getTruckStatus();
	}

	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/truck-statuses")
	public DeferredResult< ResponseEntity<List<TruckStatusResponse> >> retrieveTruckStatusList() {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveTruckStatusList -- Retrieve truck status list");

		return truckStatusDeferred.deferResponseList(truckStatusClient.submitRequestList(this.endpoint));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/truck-statuses/{id}")
	public DeferredResult< ResponseEntity<TruckStatusResponse> > retrieveTruckStatus(@PathVariable String id) {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveTruckStatus -- Retrieve specific truck status");
		
		return truckStatusDeferred.deferResponse(truckStatusClient.submitRequest(this.endpoint + '/' + id));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PutMapping("/truck-statuses")
	public DeferredResult< ResponseEntity<TruckStatusResponse> > updateTruckStatus(@Valid @RequestBody TruckStatusRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- updateTruckStatus -- update an truck status");

		return truckStatusDeferred.deferResponse(truckStatusClient.updateRequest(this.endpoint, request));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PostMapping("/truck-statuses")
	public DeferredResult< ResponseEntity<TruckStatusResponse> > insertTruckStatus(@Valid @RequestBody TruckStatusRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- insertTruckStatus -- insert a new truck status");

		return truckStatusDeferred.deferResponse(truckStatusClient.createRequest(this.endpoint, request));
	}
}
