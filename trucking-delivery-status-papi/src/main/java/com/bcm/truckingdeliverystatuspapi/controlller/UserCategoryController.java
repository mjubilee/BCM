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
import com.bcm.truckingdeliverystatuspapi.client.UserCategoryClient;
import com.bcm.truckingdeliverystatuspapi.configuration.EndpointConfiguration;
import com.bcm.truckingdeliverystatuspapi.deferred.UserCategoryDeferred;
import com.bcm.truckingdeliverystatuspapi.model.UserCategoryRequest;
import com.bcm.truckingdeliverystatuspapi.model.UserCategoryResponse;

import jakarta.validation.Valid;


@RestController
public class UserCategoryController {

	Logger log = LoggerFactory.getLogger(UserCategoryController.class);
	
	@Autowired private Environment environment;
	@Autowired private EndpointConfiguration endpointConfiguration;
	@Autowired private UserCategoryClient userCategoryClient;
	@Autowired private UserCategoryDeferred userCategoryDeferred;
	
	private String port;
	private String host;
	private	String endpoint;
	
	private void setupLocalVariable() {
		this.port = environment.getProperty("local.server.port");
		this.host = environment.getProperty("HOSTNAME");
		this.endpoint = endpointConfiguration.getUserCategory();
	}

	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/user-categories")
	public DeferredResult< ResponseEntity<List<UserCategoryResponse> >> retrieveUserCategoryList() {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveUserCategoryList -- Retrieve user category list");

		return userCategoryDeferred.deferResponseList(userCategoryClient.submitRequestList(this.endpoint));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(path = "/user-categories/{id}")
	public DeferredResult< ResponseEntity<UserCategoryResponse> > retrieveUserCategory(@PathVariable String id) {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- retrieveUserCategory -- Retrieve specific user category");
		
		return userCategoryDeferred.deferResponse(userCategoryClient.submitRequest(this.endpoint + '/' + id));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PutMapping("/user-categories")
	public DeferredResult< ResponseEntity<UserCategoryResponse> > updateUserCategory(@Valid @RequestBody UserCategoryRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- updateUserCategory -- update an user category");

		return userCategoryDeferred.deferResponse(userCategoryClient.updateRequest(this.endpoint, request));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@PostMapping("/user-categories")
	public DeferredResult< ResponseEntity<UserCategoryResponse> > insertUserCategory(@Valid @RequestBody UserCategoryRequest request) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-papi -- insertUserCategory -- insert a new user category");

		return userCategoryDeferred.deferResponse(userCategoryClient.createRequest(this.endpoint, request));
	}
}
