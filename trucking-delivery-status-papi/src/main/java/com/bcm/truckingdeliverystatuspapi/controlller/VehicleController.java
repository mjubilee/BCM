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

import com.bcm.truckingdeliverystatuspapi.client.VehicleClient;
import com.bcm.truckingdeliverystatuspapi.configuration.EndpointConfiguration;
import com.bcm.truckingdeliverystatuspapi.deferred.VehicleDeferred;
import com.bcm.truckingdeliverystatuspapi.model.VehicleRequest;
import com.bcm.truckingdeliverystatuspapi.model.VehicleResponse;

import jakarta.validation.Valid;


@RestController
public class VehicleController {

	Logger log = LoggerFactory.getLogger(VehicleController.class);
	
	@Autowired private Environment environment;
	@Autowired private EndpointConfiguration endpointConfiguration;
	@Autowired private VehicleClient vehicleClient;
	@Autowired private VehicleDeferred vehicleDeferred;
	
	private String port;
	private String host;
	private	String endpoint;
	
	private void setupLocalVariable() {
		this.port = environment.getProperty("local.server.port");
		this.host = environment.getProperty("HOSTNAME");
		this.endpoint = endpointConfiguration.getTruck();
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/vehicles")
	public DeferredResult< ResponseEntity<List<VehicleResponse> >> retrieveVehicleList() {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveVehicleList -- Retrieve vehicle list");

		return vehicleDeferred.deferResponseList(vehicleClient.submitRequestList(this.endpoint));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/vehicles/{id}")
	public DeferredResult< ResponseEntity<VehicleResponse> > retrieveVehicle(@PathVariable String id) {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveVehicle -- Retrieve specific vehicle");
		
		return vehicleDeferred.deferResponse(vehicleClient.submitRequest(this.endpoint + '/' + id));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PutMapping("/vehicles")
	public DeferredResult< ResponseEntity<VehicleResponse> > updateVehicleResponse(@Valid @RequestBody VehicleRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- updateVehicleResponse -- update a vehicle");

		return vehicleDeferred.deferResponse(vehicleClient.updateRequest(this.endpoint, request));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PostMapping("/vehicles")
	public DeferredResult< ResponseEntity<VehicleResponse> > insertVehicleResponse(@Valid @RequestBody VehicleRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- insertVehicleResponse -- insert a new vehicle");

		return vehicleDeferred.deferResponse(vehicleClient.createRequest(this.endpoint, request));
	}
	
}
